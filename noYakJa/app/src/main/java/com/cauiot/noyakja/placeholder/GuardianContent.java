package com.cauiot.noyakja.placeholder;

import com.cauiot.noyakja.DB.DBGuardians;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class GuardianContent {

    /**
     * An array of sample (placeholder) items.
     */
    public static final List<GuardianItem> ITEMS = new ArrayList<GuardianItem>();

    /**
     * A map of sample (placeholder) items, by ID.
     */
    public static final Map<String, GuardianItem> ITEM_MAP = new HashMap<String, GuardianItem>();

    private static final int COUNT = DBGuardians.guardians!=null ? DBGuardians.guardians.size() : 0;

    static {
        // Add some sample items.
        for (int i = 0; i < COUNT; i++) {
            addItem(createPlaceholderItem(i));
        }
    }

    private static void addItem(GuardianItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.name, item);
    }

    private static GuardianItem createPlaceholderItem(int position) {
        String name = DBGuardians.guardians.get(position).getName();
        String phone = DBGuardians.guardians.get(position).getPhone();
        return new GuardianItem(name, phone);    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A placeholder item representing a piece of content.
     */
    public static class GuardianItem {
        public final String name;
        public final String phone;

        public GuardianItem(String name, String phone) {
            this.name = name;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "name: "+this.name+", "+"phone:"+this.phone;        }
    }
}