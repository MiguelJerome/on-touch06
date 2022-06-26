package com.example.evaluation_formative_06_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

// Name: Miguel Jerome
// Numero etudiant:2001326
// Classe: IFM25907-030-P2022


public class MainActivity extends AppCompatActivity {

    private ImageView droidChoisi = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        droidChoisi = null;
        ImageView imageAndroidBlue = findViewById(R.id.imageAndroidBleu);
        imageAndroidBlue.setColorFilter(Color.BLUE);
        ImageView imageAndroidRed = findViewById(R.id.imageAndroidRed);
        imageAndroidRed.setColorFilter(Color.RED);

        ConstraintLayout layout = findViewById(R.id.layout_main);


        layout.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionEvent) {
                gererMotion(motionEvent);
                return true;
            }


        });
    }

    private void gererMotion(MotionEvent geste)
    {
            float x = geste.getX(0);
            float y = geste.getY(0);
        ImageView imageAndroidRed = findViewById(R.id.imageAndroidRed);


        ImageView imageAndroidBlue = findViewById(R.id.imageAndroidBleu);



            int action = geste.getActionMasked();
            switch (action)
            {
                case MotionEvent.ACTION_DOWN:
                    Log.i("TAG", "ACTION_DOWN");
                    if(positionSurView(imageAndroidBlue,x,y))
                        droidChoisi = imageAndroidBlue;
                   else if(positionSurView(imageAndroidRed,x,y))
                        droidChoisi = imageAndroidRed;
                    else
                        droidChoisi = null;
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    Log.i("TAG", "ACTION_POINTER_DOWN");
                    if(positionSurView(imageAndroidBlue,x,y))
                        droidChoisi = imageAndroidBlue;
                    else if(positionSurView(imageAndroidRed,x,y))
                        droidChoisi = imageAndroidRed;
                    else
                        droidChoisi = null;
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.i("TAG", "ACTION_MOVE");
                    if (droidChoisi != null)
                        positionnerView(droidChoisi,x,y);
                    break;
                case MotionEvent.ACTION_UP:
                    Log.i("TAG", "ACTION_UP");
                    droidChoisi = null;
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    Log.i("TAG", "ACTION_POINTER_UP");
                    droidChoisi = null;
                    break;
                default:
                    Log.i("TAG", "ACTION_??? (" + action + ")");
                    break;
            }

        }

    private void positionnerView(View v, float x, float y)
    {
        int largeur = v.getWidth();
        int hauteur = v.getHeight();
        v.setX(x - largeur / 2);
        v.setY(y - hauteur / 2);
    }

    private boolean positionSurView(View v, float x, float y)
    {
        float vx = v.getX();
        float vy = v.getY();

        int largeur = v.getWidth();
        int hauteur = v.getHeight();

        return (x >= vx && x <= vx + largeur && y >= vy && y <= vy + hauteur);
    }

    }
