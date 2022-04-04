package command.admin_command.parent;

import coliseum.Coliseum;
import main.MapConfig;

import java.util.List;
import java.util.Map;

public abstract class AdministratorCommandModel {

    protected MapConfig map_config;
    protected String value;
    protected Map<String, Coliseum> coliseum_list;

    public AdministratorCommandModel(String value, MapConfig map_config, Map<String, Coliseum> coliseum_list)
    {
        this.value = value;
        this.map_config = map_config;
        this.coliseum_list = coliseum_list;
    }
}
