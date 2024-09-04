package me.keegan.chameleon_rpg.utils.objects.classes.builders;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class LoreBuilder {
    private final List<String> lore = new ArrayList<>(Collections.singletonList(""));
    private final List<String[]> variables = new ArrayList<>();

    private String color = ChatColor.GRAY.toString();
    private boolean canWrite = true;
    private int lineIndex = 0;

    public LoreBuilder() {}

    public LoreBuilder declareVariables(String... variables) {
        this.variables.add(variables);
        return this;
    }

    public LoreBuilder write(String string) {
        lore.set(lineIndex, lore.get(lineIndex) + color + string);
        return this;
    }

    public LoreBuilder writeVariable(int variableIndex, int enchantLevel) {
        if (variableIndex >= variables.size() || enchantLevel >= variables.get(variableIndex).length) { return this; }

        this.write(variables.get(variableIndex)[enchantLevel]);
        return this;
    }

    public LoreBuilder writeIfAllowed(String string) {
        if (!this.canWrite) { return this; }

        this.write(string);
        return this;
    }

    public LoreBuilder setVariable(String... variables) {
        this.variables.add(variables);
        return this;
    }

    public LoreBuilder setCanWriteIf(boolean condition) {
        this.canWrite = condition;
        return this;
    }

    public LoreBuilder setColor(ChatColor chatColor) {
        this.color = chatColor.toString();
        return this;
    }

    public LoreBuilder setColor(ChatColor chatColor, ChatColor chatColor2) {
        this.color = chatColor.toString() + chatColor2.toString();
        return this;
    }

    public LoreBuilder resetColor() {
        this.color = ChatColor.GRAY.toString();
        return this;
    }

    public LoreBuilder newLine() {
        this.lineIndex++;
        this.lore.add("");
        return this;
    }

    public LoreBuilder newLine(boolean resetColor) {
        this.resetColor();
        return this.newLine();
    }

    public List<String> build() {
        return this.lore;
    }
}
