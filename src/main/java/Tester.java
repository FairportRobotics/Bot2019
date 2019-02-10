import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import java.util.ArrayList;
import java.util.Collections;

public class Tester {

    public static void main(String[] args) {

        boolean rev = true;
        int toADCPos = 500;

        Tester t = new Tester();



//        t.getOffset(rawVal,posVal,adcPos,rev);

    }

    public int getOffset(int rawVal, int posVal, int toADCPos, boolean reverse_turn) {

//        ArrayList<Integer> rawArr = new ArrayList<>();
//        ArrayList<Integer> posArr = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            rawArr.add(t.getSensorCollection().getAnalogInRaw());
//            posArr.add(t.getSelectedSensorPosition());
//        }
//        Collections.sort(rawArr);
//        Collections.sort(posArr);
//
//        int rawVal = rawArr.get(rawArr.size() / 2);
//        int posVal = posArr.get(posArr.size() / 2);

        System.out.println("raw " + rawVal);
        System.out.println("pos " + posVal);

        int adcPos = toADCPos - rawVal;
//        System.out.println(t.getName() + "-adcpos " + adcPos);
        int pos = adcPos * (reverse_turn ? -1 : 1);
//        System.out.println(t.getName() + "-pos " + pos);
        posVal=posVal%1024;
        if(posVal<0)
        {
            posVal+=1024;
        }
        pos = posVal + pos;
//        System.out.println(t.getName() + "-pos2 " + pos);
        int round = pos % 1024;
//        System.out.println("round " + round);
//        trueNorthEncoderOffset = round;

        return round;
    }

}
