package com.b2x2.competition.N1.Q6;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class Q6 {
    @Setter @AllArgsConstructor
    static class Airplane {
        String name, model;

        public int hashCode() { return name.hashCode(); }

        public boolean equals(Object obj) {
            Airplane other = (Airplane) obj;
            return name.equals(other.name)
                    && model.equals(other.model);
        }
    }

    public static void main(String[] args) {
        Airplane sally = new Airplane("Sally", "Boeing");
        Airplane query = new Airplane("Sally", "Boeing");
        Map<Airplane, Boolean> airplanes = new HashMap<>();
        airplanes.put(sally, true);

        System.out.println(airplanes.get(query));
        sally.setModel("Airbus");
        System.out.println(airplanes.get(query));
        sally.setName("Sally 2");
        System.out.println(airplanes.get(query));
    }
}
