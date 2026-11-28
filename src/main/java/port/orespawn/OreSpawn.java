package port.orespawn;

import java.util.Map;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod("orespawn")
public final class OreSpawn {
    static final DeferredRegister.Items ITEMS = DeferredRegister.createItems("orespawn");
    static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks("orespawn");

    public OreSpawn(IEventBus bus) {
        for (String id : new String[]{"uranium_nugget", "titanium_nugget", "uranium_ingot", "titanium_ingot", "trextooth", "worm_tooth", "worm_food", "moth_scale", "amethyst","ruby","tigerseye"}) ITEMS.registerSimpleItem(id);
        for (String id : new String[]{"uranium_ore", "uranium_block", "titanium_ore", "titanium_block", "amethyst_ore", "amethyst_block","ruby_ore","ruby_block","tigerseye_ore","tigerseye_block"}) {
            ITEMS.registerSimpleBlockItem(BLOCKS.registerSimpleBlock(id,
                p -> p.strength(5, 5).requiresCorrectToolForDrops().lightLevel(s -> 3)));
        }
        for (String id : new String[]{"alosaurus_ore", "baryonyx_ore", "camarasaurus_ore", "cryolophosaurus_ore", "pointysaurus_ore", "trex_ore", "cow_ore", "creeper_ore", "ghast_ore", "horse_ore", "pig_ore", "zombie_ore", "bird_ore", "alien_ore", "cavefisher_ore", "nastysaurus_ore", "velocityraptor_ore", "gammametroid_ore", "spyro_ore", "dragonfly_ore", "smallworm_ore", "mediumworm_ore", "largeworm_ore", "doomworm_ore", "scorpion_ore", "emperor_scorpion_ore", "basilisk_ore", "mantis_ore", "beaver_ore", "brutalfly_ore", "kyuubi_ore", "mothra_ore", "cassowary_ore", "redcow_ore", "crystal_cow_ore", "dungeon_beast_ore", "stinkbug_ore"}) {
            ITEMS.registerSimpleBlockItem(BLOCKS.registerSimpleBlock(id, p -> p.strength(0.5f, 1)));
        }
        for (String part : new String[]{"big_bertha_blade","big_bertha_guard","big_bertha_handle"}) ITEMS.registerSimpleItem(part);
        ITEMS.registerSimpleItem("big_bertha", p -> p.sword(new ToolMaterial(ToolMaterial.NETHERITE.incorrectBlocksForDrops(),5000,15,96,8,repairTag("ultimate")),3,-3f));
        ITEMS.registerSimpleItem("crystal_apple", p -> p.food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(5).saturationModifier(0.85f).build()));
        ITEMS.registerSimpleItem("popcorn", p -> p.food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(4).saturationModifier(0.4f).build()));
        ITEMS.registerItem("ultimate_bow",BowItem::new,p->p.durability(3000).enchantable(25));
        ITEMS.registerSimpleItem("emperor_scorpion_scale");
        ITEMS.registerSimpleItem("pink_tourmaline");
        ITEMS.registerSimpleItem("basilisk_scale");
        ITEMS.registerSimpleItem("green_goo");
        ITEMS.registerItem("big_hammer",BigHammer::new);
        ITEMS.registerItem("mantis_claw", MantisClaw::new);
        tools("emerald", ToolMaterial.IRON, 1000, 6.5f, 3, 12);
        tools("amethyst", ToolMaterial.IRON, 1000, 6.5f, 3, 12);
        tools("ultimate", ToolMaterial.NETHERITE, 3000, 15, 36, 100);
        tools("ruby", ToolMaterial.DIAMOND, 1800, 9, 7, 16);
        tools("tigerseye", ToolMaterial.DIAMOND, 1600, 8, 6, 15);
        armor("ruby", 120, 16, 4, 9, 7, 4);
        armor("tigerseye", 110, 15, 4, 8, 7, 3);
        armor("ultimate", 200, 100, 6, 12, 10, 6);
        armor("emerald", 100, 12, 3, 8, 6, 3);
        armor("moth", 100, 12, 2, 7, 5, 2);
        armor("amethyst", 100, 12, 4, 8, 7, 3);
        for (String[] nest : new String[][]{{"ant_block", "red_ant"}, {"rainbow_ant_block", "rainbow_ant"}, {"unstable_ant_block", "unstable_ant"}})
            ITEMS.registerSimpleBlockItem(BLOCKS.registerBlock(nest[0], p -> new AntNest(p, nest[1]), p -> p.strength(0.6f).randomTicks()));
        CrystalBlocks.register();
        Landscape.register(bus);
        CritterCage.register();
        Creatures.register(bus);
        BLOCKS.register(bus);
        ITEMS.register(bus);
        bus.addListener(OreSpawn::creative);
    }

    private static void creative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().identifier().equals(Identifier.withDefaultNamespace("ingredients"))) {
            ITEMS.getEntries().forEach(entry -> event.accept(entry.get()));
        }
    }

    private static TagKey<Item> repairTag(String name) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("orespawn", "repairs_" + name));
    }

    private static void tools(String name, ToolMaterial tier, int uses, float speed, float damage, int enchantability) {
        ToolMaterial m = new ToolMaterial(tier.incorrectBlocksForDrops(), uses, speed, damage, enchantability, repairTag(name));
        ITEMS.registerSimpleItem(name + "_sword", p -> p.sword(m, 3, -2.4f));
        ITEMS.registerSimpleItem(name + "_pickaxe", p -> p.pickaxe(m, 1, -2.8f));
        ITEMS.registerItem(name + "_axe", p -> new AxeItem(m, 5, -3f, p));
        ITEMS.registerItem(name + "_shovel", p -> new ShovelItem(m, 1.5f, -3f, p));
        ITEMS.registerItem(name + "_hoe", p -> name.equals("ultimate") ? new UltimateHoe(m,-damage,p) : new HoeItem(m, -damage, -1f, p));
    }

    private static void armor(String name, int durability, int enchant, int feet, int legs, int chest, int head) {
        var material = new ArmorMaterial(durability, Map.of(ArmorType.BOOTS, feet, ArmorType.LEGGINGS, legs,
            ArmorType.CHESTPLATE, chest, ArmorType.HELMET, head), enchant, SoundEvents.ARMOR_EQUIP_IRON, 3, 0,
            repairTag(name), ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath("orespawn", name)));
        for (ArmorType slot : new ArmorType[]{ArmorType.HELMET, ArmorType.CHESTPLATE, ArmorType.LEGGINGS, ArmorType.BOOTS}) {
            ITEMS.registerSimpleItem(name + "_" + slot.getName(), p -> p.humanoidArmor(material, slot));
        }
    }
}


