package main.utils;

import coliseum.Coliseum;

import java.util.List;

public class MapsRecuperator {

    public static Coliseum getMapByName(List<Coliseum> maps_list, String map_name)
    {
        Coliseum map = null;
        if(maps_list.size() != 0)
        {
            int i = 0;
            while(i < maps_list.size() && map == null)
            {
                if(maps_list.get(i).getName().equalsIgnoreCase(map_name))
                {
                    map = maps_list.get(i);
                }
                i++;
            }
        }
        return map;
    }
}
