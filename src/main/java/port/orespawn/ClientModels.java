package port.orespawn;
import java.util.function.Supplier;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid="orespawn", value=Dist.CLIENT)
public final class ClientModels {
    @SubscribeEvent public static void register(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(Creatures.PROJECTILE.get(), ThrownItemRenderer::new);
        bind(event,"alosaurus",Shapes::ModelAlosaurus, root -> new AlosaurusPose(root,1.5F,1f),1f);
        bind(event,"trex",Shapes::ModelTRex, root -> new TRexPose(root,1.5F,1f),1f);
        bind(event,"baryonyx",Shapes::ModelBaryonyx, root -> new BaryonyxPose(root,1.5F,1f),1f);
        bind(event,"camarasaurus",Shapes::ModelCamarasaurus, root -> new CamarasaurusPose(root,1.5F,1f),1f);
        bind(event,"pointysaurus",Shapes::ModelPointysaurus, root -> new PointysaurusPose(root,1.5F,1f),1f);
        bind(event,"cryolophosaurus",Shapes::ModelCryolophosaurus, root -> new CryolophosaurusPose(root,1.5F,0.55f),0.55f);
        bind(event,"cavefisher",Shapes::ModelCaveFisher, root -> new CaveFisherPose(root,1.0F,1f),1f);
        bind(event,"red_ant",Shapes::ModelAnt, root -> new AntModel(root),0.4f);
        bind(event,"bird",Shapes::ModelBird, root -> new BirdPose(root,0.6F,1f),1f);
        bind(event,"butterfly",Shapes::ModelButterfly, root -> new ButterflyPose(root,0.6F,1f),1f);
        bind(event,"gammametroid",Shapes::ModelGammaMetroid, root -> new GammaMetroidPose(root,1.0F,1f),1f);
        bind(event,"spyro",Shapes::ModelSpyro, root -> new SpyroPose(root,1.0F,1f),1f);
        bind(event,"dragonfly",Shapes::ModelDragonfly, root -> new DragonflyPose(root,1.5F,1f),1f);
        bind(event,"firefly",Shapes::ModelFirefly, root -> new FireflyPose(root,1.5F,1f),1f);
        bind(event,"mosquito",Shapes::ModelMosquito, root -> new MosquitoPose(root,1f,1f),1f);
        bind(event,"nastysaurus",Shapes::ModelNastysaurus, root -> new NastysaurusPose(root,1.5F,1f),1f);
        bind(event,"alien",Shapes::ModelAlien, root -> new AlienPose(root,0.1F,1f),1f);
        bind(event,"velocityraptor",Shapes::ModelVelocityRaptor, root -> new VelocityRaptorPose(root,1.0F,1f),1f);
        bind(event,"small_worm",Shapes::ModelWormSmall, root -> new WormSmallPose(root,1f,1f),1f);
        bind(event,"medium_worm",Shapes::ModelWormMedium, root -> new WormMediumPose(root,1f,1f),1f);
        bind(event,"large_worm",Shapes::ModelWormLarge, root -> new WormLargePose(root,1f,1f),1f);
        bind(event,"moth",Shapes::ModelButterfly, root -> new ButterflyPose(root,0.6F,1.5f),1.5f);
        bind(event,"kyuubi",Shapes::ModelKyuubi, root -> new KyuubiPose(root,1.0F,1f),1f);
        bind(event,"hercules_beetle",Shapes::ModelHerculesBeetle, root -> new HerculesBeetlePose(root,1f,1f),1f);
        bind(event,"triffid",Shapes::ModelTriffid, root -> new TriffidPose(root,1f,1f),1f);
        bind(event,"scorpion",ScorpionMesh::create, root -> new ScorpionPose(root,0.62f,false),0.75f);
        bind(event,"emperor_scorpion",EmperorScorpionMesh::create, root -> new ScorpionPose(root,0.22f,true),1.5f);
        bind(event,"basilisk",Shapes::ModelBasilisk, root -> new BasiliskPose(root,1f,1f),1f);
        bind(event,"mantis",Shapes::ModelMantis, root -> new MantisPose(root,1.0F,1f),1f);
        bind(event,"mothra",Shapes::ModelButterfly, root -> new ButterflyPose(root,0.1F,8f),8f);
        bind(event,"brutalfly",Shapes::ModelBrutalfly, root -> new BrutalflyPose(root,0.1F,12f),12f);
        bind(event,"beaver",Shapes::ModelBeaver, root -> new BeaverPose(root,1.0F,1f),1f);
        bind(event,"cassowary",Shapes::ModelCassowary, root -> new CassowaryPose(root,1.0F,1f),1f);
        bind(event,"termite",Shapes::ModelAnt, root -> new AntModel(root),0.4f);
        bind(event,"redcow",Shapes::ModelCow, root -> new CowPose(root),1f);
        bind(event,"stinkbug",Shapes::ModelStinkBug, root -> new StinkBugPose(root,0.1F,1f),1f);
        bind(event,"doom_worm",Shapes::ModelWormDoom, root -> new DoomPose(root),2f);
    }
    private static void bind(EntityRenderersEvent.RegisterRenderers event,String id,Supplier<ModelPart> mesh,java.util.function.Function<ModelPart,EntityModel<LivingEntityRenderState>> model,float scale){
        String[] names=switch(id){
            case "alosaurus" -> new String[]{"alosaurus.png"};
            case "trex" -> new String[]{"trex.png"};
            case "baryonyx" -> new String[]{"baryonyx.png"};
            case "camarasaurus" -> new String[]{"camarasaurus.png"};
            case "pointysaurus" -> new String[]{"pointysaurus.png"};
            case "cryolophosaurus" -> new String[]{"cryolophosaurus.png"};
            case "cavefisher" -> new String[]{"cavefisher.png"};
            case "red_ant" -> new String[]{"red_ant.png"};
            case "bird" -> new String[]{"bird1.png", "bird2.png", "bird3.png", "bird4.png", "bird5.png", "bird6.png"};
            case "butterfly" -> new String[]{"butterfly.png", "butterfly2.png", "butterfly3.png", "butterfly4.png"};
            case "gammametroid" -> new String[]{"gammametroid.png"};
            case "spyro" -> new String[]{"spyrotexture.png"};
            case "dragonfly" -> new String[]{"dragonfly.png"};
            case "firefly" -> new String[]{"firefly.png"};
            case "mosquito" -> new String[]{"mosquito.png"};
            case "nastysaurus" -> new String[]{"nastysaurus.png"};
            case "alien" -> new String[]{"alien.png"};
            case "velocityraptor" -> new String[]{"velocityraptor.png"};
            case "small_worm" -> new String[]{"wormsmalltexture.png"};
            case "medium_worm" -> new String[]{"wormmediumtexture.png"};
            case "large_worm" -> new String[]{"wormlargetexture.png"};
            case "moth" -> new String[]{"lunamoth.png", "eyemoth.png", "firemoth.png", "darkmoth.png"};
            case "kyuubi" -> new String[]{"kyuubi.png"};
            case "hercules_beetle" -> new String[]{"hercules_beetle.png"};
            case "triffid" -> new String[]{"triffid.png"};
            case "scorpion" -> new String[]{"scorpion.png"};
            case "emperor_scorpion" -> new String[]{"emperor_scorpion.png"};
            case "basilisk" -> new String[]{"basilisk.png"};
            case "mantis" -> new String[]{"mantis.png"};
            case "mothra" -> new String[]{"eyemoth.png"};
            case "brutalfly" -> new String[]{"brutalfly.png"};
            case "beaver" -> new String[]{"beaver.png"};
            case "cassowary" -> new String[]{"cassowary.png"};
            case "termite" -> new String[]{"termite.png"};
            case "redcow" -> new String[]{"red_cow.png"};
            case "stinkbug" -> new String[]{"stinkbug.png"};
            case "doom_worm" -> new String[]{"wormdoomtexture.png"};
            default -> throw new IllegalArgumentException(id);
        };
        Identifier[] skins=java.util.Arrays.stream(names).map(n -> Identifier.fromNamespaceAndPath("orespawn","textures/entity/"+n)).toArray(Identifier[]::new);
        event.registerEntityRenderer(Creatures.SPECIES.get(id).type().get(), context -> new Renderer(context,model.apply(mesh.get()),skins,scale));
    }
    private static final class Renderer extends MobRenderer<Creatures.Creature,LivingEntityRenderState,EntityModel<LivingEntityRenderState>> {
        private final Identifier[] skins;
        private final float modelScale;
        Renderer(EntityRendererProvider.Context context,EntityModel<LivingEntityRenderState> model,Identifier[] skins,float scale) {
            super(context,model,scale==0.4f ? 0 : 0.45f);
            this.skins=skins;modelScale=scale;
        }
        @Override public void extractRenderState(Creatures.Creature mob,LivingEntityRenderState raw,float tick){
            super.extractRenderState(mob,raw,tick);
            CreatureFrame state=(CreatureFrame)raw;
            state.individual=mob.getUUID().getLeastSignificantBits();
            state.attacking=mob.isAggressive() || mob.swinging;
            state.healthFraction=mob.getHealth()/mob.getMaxHealth();
            state.sitting=mob.isResting();
            if(mob instanceof DoomWorm worm){
                if(state.trail==null)state.trail=new float[100][3];
                double yaw=Math.toRadians(mob.yBodyRot),cs=Math.cos(yaw),sn=Math.sin(yaw);
                for(int i=0;i<100;i++){
                    var delta=worm.segment(i).subtract(worm.position());
                    state.trail[i][0]=(float)(-8*(delta.x*cs+delta.z*sn));
                    state.trail[i][1]=(float)(-8*delta.y);
                    state.trail[i][2]=(float)(8*(delta.x*sn-delta.z*cs));
                }
            }
            state.scale*=modelScale;
        }
        @Override public LivingEntityRenderState createRenderState(){return new CreatureFrame();}
        @Override protected int getBlockLightLevel(Creatures.Creature mob,net.minecraft.core.BlockPos pos){
            return mob.species().equals("firefly") ? 15 : super.getBlockLightLevel(mob,pos);
        }
        @Override protected net.minecraft.world.phys.AABB getBoundingBoxForCulling(Creatures.Creature mob){
            var bounds=super.getBoundingBoxForCulling(mob);
            if(mob instanceof DoomWorm worm)for(int i=0;i<100;i+=2)bounds=bounds.minmax(new net.minecraft.world.phys.AABB(worm.segment(i),worm.segment(i)).inflate(3));
            return bounds;
        }
        @Override public Identifier getTextureLocation(LivingEntityRenderState raw){return skins[Math.floorMod(((CreatureFrame)raw).individual,skins.length)];}
    }
}
