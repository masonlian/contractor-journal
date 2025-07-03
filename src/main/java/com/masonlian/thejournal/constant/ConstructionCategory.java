package com.masonlian.thejournal.constant;

public enum ConstructionCategory {
    PROTECTION( // Protection materials
            "Protective tape", "Canvas", "Protection board", "Protective film", "Foam padding"
    ),

    DEMOLITION( // Demolition materials
            "Demolition hammer", "Chisel", "Safety net", "Jackhammer", "Dust-proof plastic sheet", "Construction dust bags"
    ),

    DISPOSAL( // Disposal materials
            "Construction waste bags", "Dump truck", "Dust container", "Wooden pallet", "Waste bin"
    ),

    MASONRY( // Masonry materials
            "Cement", "Fine sand", "Medium sand", "Red bricks", "Cement bricks", "Grouting material", "Bonding agent"
    ),

    PLUMBING( // Plumbing materials
            "PVC pipes", "Elbows", "Copper pipes", "Faucet", "Water pump", "Waterproof sealant", "Silicone"
    ),

    ELECTRICAL_WORKS( // Electrical works materials
            "VVF cable", "Single-core wire", "Flexible conduit", "Switch", "Outlet", "Distribution box", "Insulation tape", "Grounding wire"
    ),

    PARTITIONING( // Partitioning materials
            "C-channel steel", "U-channel steel", "Gypsum board", "Calcium silicate board", "Glass wool", "Rock wool", "Reinforced panel"
    ),

    WATERPROOFING( // Waterproofing materials
            "PU waterproof coating", "Elastic cement", "Waterproof membrane", "Primer", "Waterproof mesh fabric"
    ),

    PAINTING( // Painting materials
            "Latex paint", "Anti-rust paint", "Primer", "Putty", "Grout", "Roller", "Brush", "Spray gun"
    ),

    FLOORING( // Flooring materials
            "Tiles", "Solid wood flooring", "Laminate flooring", "Grout", "PVC flooring", "Self-leveling cement"
    ),

    CEILING( // Ceiling materials
            "T-bar", "Suspension rod", "Gypsum board", "Calcium silicate board", "Acoustic panel", "Ceiling insulation"
    ),

    DOOR_AND_WINDOWS( // Door and window materials
            "Aluminum window", "Airtight window", "Wooden door", "Glass door", "Hinges", "Lockset", "Door frame", "Window frame", "Weather stripping"
    ),

    CARPENTRY( // Carpentry materials
            "Blockboard", "MDF", "Plywood", "Decorative panel", "Drawer slides", "Hinges", "L-bracket"
    ),

    GLASS_AND_METAL( // Glass and metal materials
            "Tempered glass", "Frosted glass", "Aluminum", "Ironwork", "Stainless steel", "Glass sealant", "Welding material", "Clamp fittings"
    ),

    LIGHT( // Light fixtures and accessories
            "Ceiling light", "Downlight", "Track light", "LED lighting", "Switch module", "Dimmer", "Lighting fittings"
    ),

    HVAC( // Heating, ventilation, and air conditioning
            "Split-type air conditioner", "Copper pipe", "Insulation foam", "Drainage pipe", "Air duct", "AC stand"
    ),

    SMART_SYSTEM( // Smart home system components
            "Smart control hub", "Temperature sensor", "Humidity sensor", "Smoke detector", "Smart lighting module", "Motorized curtain", "Smart lock", "Remote control"
    ),

    FURNITURE( // Furniture items
            "Desk", "Wardrobe", "Bed frame", "Dining table", "Sofa", "Drawer hardware", "Drawer slides", "Acrylic panel", "Veneered board"
    );


    private final String[] materials;

    ConstructionCategory(String... materials) {
        this.materials = materials;
    }

    public String[] getMaterials() {
        return materials;
    }
}
