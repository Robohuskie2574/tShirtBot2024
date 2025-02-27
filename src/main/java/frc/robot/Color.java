package frc.robot;

import java.util.ArrayList;

public class Color{
    float abs(float x){
        if(x>0){
            return x;
        }else{
            return -x;
        }
    }
    float clamp(float x, float min, float max){
        if(x<min){
            return min;
        }else if(x>max){
            return max;
        }else{
            return x;
        }
    }
    public float r,g,b,a;
    public Color(){ //Deafault is solid black
        r=0;
        g=0;
        b=0;
        a=1;
    }
    public Color(float r, float g, float b){ //Create with RGB
        this.r=r;
        this.g=g;
        this.b=b;
        this.a=1;
    }
    public Color(float r, float g, float b, float a){ //Create with RGBA
        this.r=r;
        this.g=g;
        this.b=b;
        this.a=a;
    }
    public Color(Color InColor){ //Duplicate color
        r=InColor.r;
        g=InColor.g;
        b=InColor.b;
        a=InColor.a;
    }
    public void SetHSV(float h, float s, float v){ //Set HSV
        h=h%360;
        r=clamp((abs(180-h)/60)-1, 0, 1); //Getting Channels. Assume full saturation and lightness.
        g=clamp(2-((abs(120-h))/60), 0, 1);
        b=clamp(2-((abs(240-h))/60), 0, 1);
        r=(r*s)+1*(1-s); //Apply sat
        g=(g*s)+1*(1-s);
        b=(b*s)+1*(1-s);
        r=r*v; //Apply Lightness
        g=g*v;
        b=b*v;
    }
    static Color Mix(boolean FirstPriority, boolean ForceFullAlpha, ArrayList<Color> Colors){
        Color ReturnColor = new Color(0,0,0,0); //Create new color, totaly blank and transparent
        float Remaining = 1; //Only used if FirstPriority
        for(Color ThisColor : Colors){ 
            float NewAlpha = ThisColor.a; //Alpha of the new color
            if(FirstPriority){
                if(NewAlpha > Remaining){ //Make sure the color is not adding too much
                    NewAlpha = Remaining; 
                }
            Remaining-=NewAlpha;
            }
            ReturnColor.r+=ThisColor.r*NewAlpha; //Adding the new color with the new alpha
            ReturnColor.g+=ThisColor.g*NewAlpha; 
            ReturnColor.b+=ThisColor.b*NewAlpha;
            ReturnColor.a+=NewAlpha;
        }
        if(ReturnColor.a!=0){ //Un-multiply the alpha.
            ReturnColor.r=ReturnColor.r/ReturnColor.a;
            ReturnColor.g=ReturnColor.g/ReturnColor.a;
            ReturnColor.b=ReturnColor.b/ReturnColor.a;
            if(ReturnColor.a>1 || ForceFullAlpha){
                ReturnColor.a=1; //Make sure the final alpha is 1 if it is needed to be that way.
            }
        }
        return ReturnColor;
    }
}