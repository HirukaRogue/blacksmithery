package net.hirukarogue.blacksmithery.miscelaneous;

import net.neoforged.neoforge.registries.datamaps.DataMapType;
import oshi.util.tuples.Pair;

public record DataMapData (DataMapType<?,?> type, Pair<?,?> data) {
}
