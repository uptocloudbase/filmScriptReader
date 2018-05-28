package org.agd.lambda.to;

import java.util.ArrayList;
import java.util.List;

public class MovieCharacterTO {

    private String name;
    private List<MovieAppearanceTO> appearances = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MovieAppearanceTO> getAppearances() {
        return appearances;
    }

    public void setAppearances(List<MovieAppearanceTO> appearances) {
        this.appearances = appearances;
    }
}
