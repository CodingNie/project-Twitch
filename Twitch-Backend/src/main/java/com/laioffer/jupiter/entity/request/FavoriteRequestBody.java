package com.laioffer.jupiter.entity.request;

import com.laioffer.jupiter.entity.db.Item;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FavoriteRequestBody {

    @JsonProperty("favorite")
    private Item favoriteItem;

    public Item getFavoriteItem() {
        return favoriteItem;
    }
}



/*
package com.laioffer.jupiter.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.laioffer.jupiter.entity.db.Item;

public class FavoriteRequestBody {
    @JsonProperty("favorite")
    private Item favoriteItem;

    public Item getFavoriteItem() {
        return favoriteItem;
    }

}
*/
