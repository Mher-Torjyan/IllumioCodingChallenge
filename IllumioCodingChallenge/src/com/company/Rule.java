package com.company;

public class Rule {

    //Assuming 1 bit for direction and 1 byte for protocol as per assignment.
    //Could be changed if more are added
    private boolean direction;
    private byte protocol;
    //In java hard to save unsigned 16 bit values.
    //In C++ I would have used unsigned 16 bit.
    private int portLowerBound;
    private int portUpperBound;
    //Similarly as above, could use unsigned int unfortunatly Java does not support.
    private long ipAdressLowerBound;
    private long ipAdressUpperBound;

    public Rule(String toParse) {

        String[] input = toParse.split(",");
        direction = getDirection(input[0]);
        protocol = getProtocol(input[1]);


        if (input[2].contains("-")) {
            String[] portRange = input[2].split("-");
            portLowerBound = getPort(portRange[0]);
            portUpperBound = getPort(portRange[1]);
        } else {
            portLowerBound = getPort(input[2]);
            portUpperBound = portLowerBound;
        }
        if (input[3].contains("-")) {
            String[] ipRange = input[3].split("-");
            ipAdressLowerBound = 0;
            ipAdressUpperBound = 0;
            for (int i = 0; i < ipRange.length; i++) {
                String[] val = ipRange[i].split("\\.");
                if (i == 0) {
                    ipAdressLowerBound = getIPAddress(val);
                } else {
                    ipAdressUpperBound = getIPAddress(val);
                }
            }
        } else {
            ipAdressLowerBound = 0;
            ipAdressUpperBound = 0;
            String[] val = input[3].split("\\.");
            ipAdressLowerBound = getIPAddress(val);
            ipAdressUpperBound = ipAdressLowerBound;

        }
    }

    public boolean getDirection(String dir){
        if (dir.equals("inbound")) {
            return true;
        } else {
            return false;
        }
    }

    public byte getProtocol(String prot){
        if (prot.equals("tcp")) {
            return  0;
        } else if (prot.equals("udp")) {
            return 1;
        }
        return -1;
    }

    public int getPort(String portIn) {
        return Integer.valueOf(portIn);
    }

    public long getIPAddress(String[] ipIn) {
        long temp = 0;
        for (int i = ipIn.length - 1; i >= 0; i--) {
            temp = temp | (Long.valueOf(ipIn[i]) << ((3 - i) * 8));
            temp = temp | (Long.valueOf(ipIn[i]) << ((3 - i) * 8));
        }
        return temp;
    }



    public boolean checkIfMatch(String dirIn, String proIn, int portIn, String ipIn){
        int matches = 0;
        if(getDirection(dirIn) == direction){
            matches++;
        }
        if(getProtocol(proIn) == protocol){
            matches++;
        }
        if(portIn >= portLowerBound && portIn <= portUpperBound){
            matches++;
        }
        long ipCheck = getIPAddress(ipIn.split("\\."));
        if ((ipCheck >= ipAdressLowerBound) && (ipCheck <= ipAdressUpperBound)) {
            matches++;
        }
        if(matches == 4){
            return true;
        }
        return false;
    }

}
