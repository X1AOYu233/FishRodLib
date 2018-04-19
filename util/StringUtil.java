package util;

public class StringUtil {
    public static long stringToLong(String sourceNum){
        long result = 0;
        char NumArray[] =  sourceNum.toCharArray();
        for (int i=0;i<NumArray.length;i++){
            char cache =  NumArray[i];
            switch (cache){
                case('0'):{
                    break;
                }case('1'):{
                    result += Math.pow(10,(NumArray.length)-i-1);
                    break;
                }case('2'):{
                    result += 2*Math.pow(10,(NumArray.length)-i-1);
                    break;
                }case('3'):{
                    result += 3*Math.pow(10,(NumArray.length)-i-1);
                    break;
                }case('4'):{
                    result += 4*Math.pow(10,(NumArray.length)-i-1);
                    break;
                }case('5'):{
                    result += 5*Math.pow(10,(NumArray.length)-i-1);
                    break;
                }case('6'):{
                    result += 6*Math.pow(10,(NumArray.length)-i-1);
                    break;
                }case('7'):{
                    result += 7*Math.pow(10,(NumArray.length)-i-1);
                    break;
                }case('8'):{
                    result += 8*Math.pow(10,(NumArray.length)-i-1);
                    break;
                }case('9'):{
                    result += 9*Math.pow(10,(NumArray.length)-i-1);
                    break;
                }default:{
                    throw new IllegalArgumentException("Not a Long-int String ");
                }
            }
        }
        return result;
    }
}
