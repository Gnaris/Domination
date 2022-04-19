package classification;

import lombok.Getter;

@Getter
public enum KitList {
    EPEISTE("Épéiste"),
    ARCHER("Archer"),
    MAGE("Mage"),
    INCOGNITO("Incognito");

    private String name;

    KitList(String name)
    {
        this.name = name;
    }
}
