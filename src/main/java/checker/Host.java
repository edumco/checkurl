package checker;

import java.util.ArrayList;

public class Host {

    public static final int ID = 0;
    public static final int IP = 1;
    public static final int PORTS = 2;

    String id;
    String ip;
    ArrayList<String> ports;

    public Host(String[] rawRecord) {

        this.id = rawRecord[Host.ID];
        this.ip = rawRecord[Host.IP];
        this.ports = parsePorts(rawRecord[Host.PORTS]);
    }

    public ArrayList<String> getEndpoints() {

        ArrayList<String> endpoints = new ArrayList<String>();
        ports.forEach(port -> endpoints.add(ip + ":" + port));

        return endpoints;
    }

    public ArrayList<String> parsePorts(String rawPorts) {

        ArrayList<String> ports = new ArrayList<String>();

        for (String port : rawPorts.split(",")) {

            if (port.contains(":")) { // Its a range of ports

                int begin = Integer.valueOf(port.split(":")[0]);
                int end = Integer.valueOf(port.split(":")[1]);

                for (int i = begin; i == end; i++) {

                    ports.add(Integer.toString(i));
                }
            } else if (port.isBlank() == false) {

                ports.add(port);
            }
        }
        return ports;
    }

    @Override
    public String toString() {

        return id + ";" + ip + ";" + ports;
    }
}
