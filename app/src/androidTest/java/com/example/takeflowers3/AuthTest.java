package com.example.takeflowers3;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AuthTest {
    @Test
    public void check_isCorrect() {
        Shop shop = new Shop("магазин","улица","89271112233",1);
        assertEquals("магазин", shop.getShopName());
        assertEquals("улица", shop.getShopAddress());
        assertEquals("89271112233", shop.getShopTelephone());
        assertEquals(1, shop.getDostavka());
    }
}