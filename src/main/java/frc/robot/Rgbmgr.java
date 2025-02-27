package frc.robot;

import java.util.ArrayList;

public class Rgbmgr{
    public static final int StripLength = 108;
    public static final ArrayList<Color> AllOneColor(Color usedColor){
        ArrayList<Color> OutputList = new ArrayList<Color>();
        for(int i = 0; i<StripLength; i++){
            OutputList.add(new Color(usedColor));
        }
        return OutputList;
    }
    public static final ArrayList<Color> RainbowHue(float squish, float shift, float s, float v){
        ArrayList<Color> OutputList = new ArrayList<Color>();
        for(int i = 0; i < StripLength; i++){
            Color ThisColor = new Color();
            ThisColor.SetHSV(squish*i+shift,s,v);
            OutputList.add(ThisColor);
        }
        return OutputList;
    }
    public static final ArrayList<Color> MaskChannels(boolean r, boolean g, boolean b, boolean a, ArrayList<Color> FirstList, ArrayList<Color> SecondList){
        ArrayList<Color> OutputList = new ArrayList<Color>();
        for(int i = 0; i < StripLength; i++){
            Color First=FirstList.get(i);
            Color Second=SecondList.get(i);
            OutputList.add(new Color(r?First.r:Second.r,g?First.g:Second.g,b?First.b:Second.b,a?First.a:Second.a));
        }
        return OutputList;
    }
    public static final ArrayList<Color> SelectSectionLR(ArrayList<Color> InputData, float Left, float Right, float Left_Ease, float Right_Ease){
        ArrayList<Color> OutputList = new ArrayList<Color>();
        for(int i=0; i<StripLength; i++){
            Color ThisColor = new Color(InputData.get(i));
            if(i<Left){
                ThisColor.a=Math.max(1-((Left-i)/Left_Ease),0);
            }else if(i>Right){
                ThisColor.a=Math.max(1-((i-Right)/Right_Ease),0);
            }else{
                ThisColor.a=1;
            }
            OutputList.add(ThisColor);
        }
        return OutputList;
    }
    @SafeVarargs
    public static final ArrayList<Color> Mix(boolean FirstPriority, boolean ForceFullAlpha, ArrayList<Color> ... ColorArrays){
        ArrayList<Color> OutputList = new ArrayList<Color>();
        for(int i = 0; i < StripLength; i++){
            final ArrayList<Color> ColorsToMix = new ArrayList<Color>();
            for(ArrayList<Color> ColorArray : ColorArrays){
                ColorsToMix.add(ColorArray.get(i));
            }
            OutputList.add(Color.Mix(FirstPriority, ForceFullAlpha, ColorsToMix));
        }
        return OutputList;
    }
    public static final ArrayList<Color> Root(){
        ArrayList<Color> RainbowHue_D = RainbowHue(Rgbdyncfg.RainbowSecSquish, (float)(Rgbdyncfg.RainbowSecShiftSpeed*Rgbdyncfg.RGBShiftTimer.get()), 1, 1);
        return RainbowHue_D;
    }
}