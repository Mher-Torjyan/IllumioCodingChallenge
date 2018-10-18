package com.company;

public class Main {

    public static void main(String[] args) {
	    Firewall firewall = new Firewall("/Users/mtorjyan/Work/IllumioInput.csv");
        System.out.println("--------------------GIVEN TEST--------------------");
        System.out.println(firewall.accept_packet("inbound",  "tcp",  80,  "192.168.1.2"));
        System.out.println(firewall.accept_packet("inbound",  "udp",  53,  "192.168.2.1"));
        System.out.println(firewall.accept_packet("outbound",  "tcp",  10234,  "192.168.10.11") );
        System.out.println(firewall.accept_packet("inbound",  "tcp",  81,  "192.168.1.2"));
        System.out.println(firewall.accept_packet("inbound", "udp",  24,  "52.12.48.92"));
        System.out.println("--------------------GIVEN TEST--------------------");

        System.out.println(firewall.accept_packet("inbound", "tcp",  32011,  "253.1.1.1"));
        System.out.println(firewall.accept_packet("outbound", "tcp",  20000,  "101.1.1.1"));
    }
}
