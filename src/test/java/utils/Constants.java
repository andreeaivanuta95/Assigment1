package utils;

public class Constants {
    public static String COOKIE_PANEL_BUTTON_XPATH = "//button[normalize-space(.) = '%s']";
    public static String SIDEBAR_MENU_BUTTON = "//button[contains(@aria-label, 'Open sidebar')]";
    public static String BIG_RESOLUTION_ACTIONS_BUTTON_XPATH = "//button/span[text() = '%s']";
    public static String SMALL_RESOLUTION_ACTIONS_BUTTON_XPATH = "//div/span[text() = '%s']";
    public static String MENU_LIST_ITEM_XPATH = "(//xm-menu-list-item/div | //a)[normalize-space() = '%s']";
}
