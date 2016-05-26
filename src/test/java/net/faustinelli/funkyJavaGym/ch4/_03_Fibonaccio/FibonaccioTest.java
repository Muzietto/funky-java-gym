/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch4._03_Fibonaccio;

import org.junit.Test;

import java.math.BigInteger;

import static net.faustinelli.funkyJavaGym.ch4._03_Fibonaccio.Fibonaccio.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Marco Faustinelli (Muzietto) on 4/18/2016.
 */
public class FibonaccioTest {
    private String fib_5000 = "62763028004889570860352531"
            + "0834968405547852870273645743902582444892793725681166326447588"
            + "3711527806250329984690249846819800648580083040107584710332687"
            + "5965621850736404222867992399326157971059747108570954873428203"
            + "5130747714187501217687430715601622996583258913777972497385436"
            + "2777629878229505500260477136108363709090010421536915488632339"
            + "2407569879741225986035919203068749267556003618653543304446819"
            + "1515469574185196007108994401531930012857410766275705479064815"
            + "2751366475529121877212785489665101733755898580317984402963873"
            + "7381870001207378241931620113992005474240344408362397262757659"
            + "0119091451301321713205098806483202478337058378932410905244971"
            + "7186857327239783000020791777804503930439875068662687670678802"
            + "9142697848170225670880694962311114079089533139023985296550560"
            + "8222859871588236577946990246567571569918722565587824066859954"
            + "7496218159297881601061923195562143932693324644219266564617042"
            + "9342278933711798323896428952854012638753426404680173789259214"
            + "8358011127805504425419838226556739594643180330430432686507774"
            + "2925818757370691726168228648841319231470626";

    private String fib_10000 = ""
            + "544383731135652813387342609937503801353891845546959670262477158"
            + "412085828656223490170830515479389605411738226759780263173843595"
            + "847511162414391747026429591699255863341179060630480897935314761"
            + "084662590727593678991506779600883065979666419658249377218003814"
            + "411588410424809979846964873753371800281637633177819279411013692"
            + "627509795098007135967180238147106699126442147752544785876745689"
            + "638080029622651331113599297627266794414001015758000435107774659"
            + "358053625024617079180592264146790056907523218958681423678495938"
            + "807564234837543863426396359707337562600989624626687461120417398"
            + "194048750624437098686543156268471861956201461266422327118150403"
            + "670188252053148458758171935335298278378003519025292395178366894"
            + "676619179538847124410284639354494846144507787625295209618875972"
            + "728892207685373964758695431591724345371936112637439263373130058"
            + "961672480517379863063681150030883967495871026195246313524474995"
            + "052041983051871683216232838597946272459197714546282183996957892"
            + "237989121994317754697052161310810965599506382972612538482420078"
            + "971090547540284381496119304650618661701229832889643527337507927"
            + "860694447618535251444210779280459799045612981294238091560550330"
            + "323389196091622366987599227829231918966880177185755555209946533"
            + "201284465023711537151417492909131048972034555775071966454252328"
            + "620220195060914835852238827110167084330511699421157751512555102"
            + "516559318881640483441295570388254775211115773957801158683970726"
            + "025656148249564605387002803313118614853998053970315557275296933"
            + "995860798503815814462764338588285295358034248508454264464716815"
            + "310015331804795674363968156533261525095711274804119281960221488"
            + "491482843891241785201745073055389287178579235094177433833315068"
            + "982393544219888054293324403711948672155435765485654991345192710"
            + "989198026651845649278278272129576492402355075955582056475693653"
            + "948733176590002063731265706435097094826497100387335174777134033"
            + "190281055756679317894700241188030946040343629534719974613922747"
            + "915497303564126330742308240519999961015497846673404583268529603"
            + "883011207656292459981362516523470939630497340464451063653041636"
            + "308236692422577614682884617918432247934344060799178833606768467"
            + "11185597501";

    private String fib_100000 = "4202692702995154386319005101293915131773915702632234503304716087198335731457276226633938477267013660962533661702858329186641162298822215333733574147268614522205177960360216576292096795530656502537998314495026330500620719088898984643619599926476236108318505023749864703859491024686621241730682736115723551647724257547502352412468746074851053353923438703547870019701586274514903943581778012410826464461823272924826749362282954004235923662667858166740323769532233540810434266661679738865959304652017245761094495566071167054301690895714604884013679491394566493844646298912078940644595782507997928878739392985610180101343882600283820398139200927163512122969924839839463533622369599880593624548314955919982731440664606860389674767846630714238798520061220948360195842828694894114956887994407533188621624706043127847588617179645038305434988777154546903665027721615391983839969917881343981419443688092501114818197984022611661046073180322923553766037586498499974540204693791793047963222486139060147413637230339253443256608485701564784891053397926289664177821919706286664135844377802876352562122389353466102826235289263234488363199404159286560829728598821205147493568392160744064818153706709889719108606083648795534004972817295465584196082602156304071901493325048737897039612705399999458459884586324576356850465902800682163360223018811383011208727253121314804106183630746333917234788155584199422004369734794567464293731590299016321476910276047953145737133530620933370013274962876666024092378144462247911861242773628847211726253860103658427961076487163331347200528488644744897867222184279861410512996912357518380420226522996351489992015929613369585992284921472840596751824858206083370375175870696347451742484480895089618955310799475473330332388074551229896105111703918074819309463129676526583523254006198015481044159254418528609134434118408385912583154979720111735082542883519942050207089706291319323705936118699708767405215366496845718246640603638302177053284851442532497454630999497577583104521012195115814606785903295807850171689359081326188293931649613908497264994236206250488864434829436295195232851838550923528889484987751381022320088255160033641645914092823188828485453780754751793048907600025512141470637066402501872547952277141425050621431726133512376491993264368219026824677798278050012296677681338164667973991346578705512745343946944045970777191879954073954276059158130792852127416568328282956215993174690589582184622213311123743528370969527697376659264209701430460262628089511749784920754475292453555824485037081169662961361437456685569229108353804447871639780202320840236093709222541404770096912820491605767773669730366536498834923648247283567638754132943595918097552972633716970745724835609042802322527268722270064679384555006784008088065427716480324882491803961614497071569285030997191355977454006784666563982465863641444396717248234018026243961781706483360371717605953991771045496130564628772749130877955433893243191369395281413812395013590807898227658488263250802618524850762756185439484575939335126157545942937679620715509540065149312865564443708655439771673542181054558453697618966155551804171330106742714737433118616111666800134742357715495644208822055262531207630247540274901297665161330833084658587180009012735858860387323290477368590430709381480698925172403629520614399575937151757870535045340264867187154085515769299670425865012093497499458084071483820091588806573538923913704024350619657151098348052986098255110975492885336115545257393042442696791158576341900753200960006019671663122779964553274124613827002842207651443127215907411334080034890407625849192846588580412869163865154796429776325124265243357090373279994090608028914324200216278403137569688793319327179281513651857484816495100553151506872893457040698364864908926339042055054701374510871324934497999408637497701279716028106056465342577613367511844757858239618765128089472486851278975083193508109837771994103670102484129663885788768053596123033251579123980971589651437698106274532939294318318049638382537268302829198532880152940714337232086321104061644358354382664864917218438210293517424553745505758090753355882025205602453784317543553853199425081002171431279765462841928691038739447760277789370517385306279744047722489086714748028855582994177874607142469064610509456252022128258329950255642602299905929046841595791167921512858860328296849951271553446508878296327611803906735633266224123093687573746634252135170679694981289617944100773168818654945023743438512318640838968116604957523873700674044602618648483425343480172570393405927238389192205890319989061851984210383082164165286102068738694744762282270671562080897567280776109321283700776536592362521370175610492010490647926435022313362357037672820456385127428485063107497131400081807697966418670920862707517582909049383024204603334049254656583584442952963370887765452655876066062446937832610287823426705006187125841909320161763987566513486927154010085083647269535513915816758710288791204561224527095476616464634423436358506846144301437379657717790829502874496253094716740270948624055817414650104670627976517803754955899088266937466501789934094158017587793129776426891242809581667986926232813625576760050205090619888399123409239688442164270550778132287725184933490420599495679264605659110257760825014193077871965880962249652458201556815237466699463967549092715904279567434739404713888403457003339771465555694947195071035775250811866532575065892797554153301608090613307672021657715401873436452152566925919864525536834568235530705981621300855049045749462125005586100312191552245274122445172305460076489568744037811637120488942481442457383881221646993921230130822937656219514811987478059283006119165854697271444230766548407257874820601218362249866393721509747526537539677353958519844769577322142446097980810172948625060754655647850574348574673760923656634582655495694500804946625600683003303969791972728978548772898691700233344838111143624421997248348373393817914540838366741137930672543630607318584213889310473297461294643309134066151220872533746202317748637999040114037035041286112690264736393397273817924315745503107186029046465168489664991484955836275210278398095989067353453771747035442365355445747127447923214040603969451728451202645051877393660878207404279794929371571338262325793789167979461085931415095683486296162401875755894130120407378671712437729188487520121912910810326749154096040648754248927534521962074102425291913766944369032132103550933382245399558387757904745511758965088027854937299536471846566637636919001351398679746388509468405284549140756479486056457920649429617829741961946109703629304530913466716238442148820189449581503029766353579703388699920365905324047373120549655643341862622777074389646423179579674577956341322830784916026813548454623362949055914885337880398436974918071336089580020123110991614556127897128629528458209067952194326976896997752127042336429246818734853834742889330980560103357922288676566480206614652444443461774011721264398205623102431248408168327688547753045794176361665903058552581366119249019699162802496454086053321275062840623398173054019398759351215711987306636178410283089779146732418239902330676994111916249584184821290161741884890744574259103285106768945456293864755738899456354943259853902533118320431113938205766012422223175295440659963277854864852984742083179611314290857560405710728212958906402305931907418946110255773607562277250486349227222087557966804673075308805002278223894683614028706639026079344344434686294556396103123818136981228794370785368388506599135026980613330934094961793563094627199853201613193153865108452721786814140586572232010017459153962501788701203805098164119780148853692024087086122573636233064502251961233974992366640735451891044862799462188815582042386580030801106890097238687055452552110829965059161245343415161008354431626917063844116549102496993880762448490819042269804160074405392450174036209847483710949092804116773547479425117696723576173189547660596984813143044906939144736572025112530913402350316579592727107140204745792181312643267980166276213539787318686784516646409043739461341058341866130194515206728071388017201750465345177683567166246451096874716792230890148549731651715426719134701779009646503713855713690614979894889941888457297282875954463903599095148708206058572338514158627674241149795154024155999938386472330121596245377182900950030617269384713599791193454044355416529598060101173170994161856361550265787123369355532596323286384309305409145251652049876541298945371331714297379550668239431873282678293074955266864563208743768540095516367311985558520002873697087726788222240830142818294228089082545681401285437906803150262330031355435708411061568390519438584930325978406002583613274547851416495855586226252253360321674766981620421827339385206593739096885252787145406142672658262177573655048328267368597269217457845651924361951505134294767813855422501312640796469744649758696322680162740933123199953154087011991048812393111727123352964379020421420583756994849612766818894376697073240511064803717146258228885676833210858702603529846510370844972539194851074168242379857338076991793231848897846533495471513960873594311483932323174971369007675150619831918752629632940698780673005828943186361408536433545182513621733366469934325860335901609292835044356195560151952535903838669632931421195319086416029491768186604338108617856893658652762599159576463089057484057012421874561183369156744400271989993859435242606882312306519905393345015702439093202555708305829478488168303432296270227178110341320372986862855706153836192552748513974514717282608166467373122237687789655395096239211535388318181125900905930703765521017233830956585103471622343721470945239512383177898180368057660537081723959944939544511007394960086606314007369726809860353148028035639179259295514035556750901346225565651200500040388964695765286839375894326641359638758981952190977233092636192929286884271506009538217214933792744247421149897522229185157042978227719340975109319968827282928000446381475380613389725119957350959904451122273439971539673040298002820751768235827912095052102735663217352194962362184727464644960226149981751908352203306342021592050720598795035239228195869165158542449929474548106605050117625206972350963702019752601985260677384221896524605299854929995338755313295340940138314907468467139499677720572636120372107374342868057717426582051294080969959563531911282739990097960539607984437523116507734719777809570433883645270239644294309466315623904013251732165782921533830102558013937152598856329347755255719232808344589151922043864806081186297548489573356845798244178775602686253784216073315926315117006546664792964827288742401180295584471128695425297779253841606405927427313508860836696395679355243026261886885752006481760497725961130073922590764478449738830065772275665777414636443696093953753681025203011970464541713411933975735103788738392769375565506845210536678318283945400401975534721292450383336003817385678921281713802749790225543274326172443844383519165927950001713682536758546723469033733698464059513247716076530428565235979898018682260049948150428797107476676459294460386409882046653439741904221084760506651625666078950470073700364878268487810943704123780820867203211027983637336090586543818602974928781155484266146415144583159391700957591524192858746462325799289808212663991897979203096513770030043332559965757095804880516280318291317892283539611806096480760588324398833851927871266308437436655196451140800936247852263042630333253021422636233953558646545567952000386740056413841503316500443196882791862089512040487190834318318720805613880482722099504496769830259462446643387340087559348080783121714848722685337702205260808038256129653712130928142868326732024547341918409543265770751846027970701058154304742603541295214529990635988000113171975043316669542398374118000586212749941657916328896392364001183403234069404287388451847652826410122033605624323121635568975986369749647596502871188585784355101687504624636539324552490154032140569865547304245954636331164655521637183906414415309758712816906121425865963519572977922422193998635333872816316775329382888285388337632233238036645089499874738060057408898360753928123721929221887935357828140069065634778202887507881813240782624394992260288324899145727374030782190991736334686587324734925180822573968054570812461164995383965527453691487340952630177567364936503105040012083733323507697152712523147323433715982381154982902092524326981085372148717838722362846968085831693638384498765389697888945985515666277324765372113023407091352370001332816569293276900781471078643097906578447793178960543143205024459597894087211433825957259050676114083777577666949879034498423616833565457642039910618453618935750101710017725620355769563378953758665795785319349166712506777600201116719824482067962138213637479996078388947476976854371179482021389349861181740828990847397711566219575730166769531616390028616437744333987950465924649624221361582556979790545498220365919158604912020715635631040315538986628041330966376721410914045182032024057167118820434464793928749803819352938145054343275764367252195300947748256245931417922605137017689402364247601489831981249881815659363767625500657693805874763140615448155779212535276526235730433689192685254054526262411911016156357795666960065908532277002703275187121877171252803456126830235101667545124754877133773716679551420426703467351042791173384629237801027761041683371686184253726562037513732843533633565163220145118782869823125951421106201364541930300041723532889519178318955856530899618414036372766242613462981832366718516227612929064474842642670720228351684614265920516762231207830828736495674753147731828095679250957455183208640720034073280476977250429065166775862662137940822216639672500136596904942830895605490951255968987922101774658817948774345231590358526032183309667099112583393485448487247848989986555106163389286689996700225455725201192468963399245341930568628287266154554977591171677234870273109994284553066290629230944606505961324859550418277938672668519564635104078617839799413872033532827843195645709007893008947027923548105184401821727997576067980745684603001508138923452502105044934874146471722564995367164821236866037139904194334377622398866638519217567726283183853832672567439807319989466480664493203325667248130169442386260260938595262185473829829655664167239056097352374183107195265095683795670742646740683849734475169685935789219642416124979114398039590666990374976005078576331036100207225998255550662603112543323693395536358218289831122170914531594780311067952757440640496806878780414499789323112454004235627907950496053532320798797946603624816863361484297838295038330096430888224575734937428832687311310335645328966004829358961398637161232521561088632564699206653873837378293823902017672262403793232643723649636576550174442401991112661121854039511616612290979354350942496682148176886761519933539302375974268295207970549690201597108189357323757477303432824688714733897849865805712867879245604618677327572104101392804594601734946451198976706954026926325977085411919015691045995552207351293138187161405499084909554845419434931917344430522376469392263403392646510051744603644570754463847534733966705557908398431759668907930084657438598561912663124717703955390006624505825330361779064559334274155743297547382435851687558291288934724053570713445458222971933730758205897144472889089781340754148110731186122936859973310507122731462503393873897738479354688284953678332216656064611036606566593132311955013694851138439291211561574498547816003618955409622344616927979949484656636148330699173829550144656063429037359575915469558527253860731802499652816768425443425889739073884352974610591022393415807247569583300801154557691634376230748190087515083604267146590376806793177153184509444555078218804371479017254048737590623414381181091777995894452851110954312451007661884236739962192456077969511122779364407990415119514440661546965008576217977093190909583132987787434522165255034445404702763169714368762469620043258522003356436848692882916179287819142433475654756287226124351664343691962489756968151414727888765534977698006480871154761766945959111922927429454384882497333914219571616022292224906364296597972099547195800025036746754894650280064556031370644217419634227831878046942804047499799250678440344926170781018652689185600559289478435620230506562090798332784054409933754325466268996342381853087817159138132409688240004900823384875229448700461035381883418217015178779468503346298941683080899357908203402596981571019152124322599142297993961017488797887830281192191238727922353623295034097047026384576950518160676657044486620981891268715888390683926816266566045504069411325517726301593005468118625314041176546254147484954209655218329262550835896721811635441831388710685040170614684768874135589248534951236185901475459798978133816784134989257021373591925918938505117120958353354468195902418584460471378852709468342796348047587156573415690896444525395795251837597314216029681638412088153036099966996188639795041806972170297532612770372422177442399757860752244275767990624899121340496474889285918528422833247118694861120447658840271122911279114792252504327153033684366578150172821878605530320299879588079217379263325468352926442085153146379821322093120797035112094626071007302778912072566743084284949202405450391750643121078577065441187219944044713259661653295743027549410956701543326099389775148130009541828402651298935227760199983004106141718321080195789503790459326081276788138994738324227512564849341286531071052868689648644035294619832639219517599262158567853308180854622517432057724041779265437756658172231185312218755767693801703046218276691467762250144758754615144514318255923066943062603732646568363555326160331715437535324349374224250967669972209569182920399221779749244067926548005910615674789500542998017635444037775787063172034013555643683051468017023738859881563516237835944674355662760135149674301440420415171928872374647867382781971827035983886036616187357262700493342349286117240506228275121275481554264228471521735497145802473814776450360970418983518376319628772221549149786891339743058279126510663209664415284380540087275689926332447230094477529152928444134842867829072123694329242420883604363950605506758772124314102728605283875613848102406526257247712999775694643500056233401552208865282718695548243000376564479084893901000536841057522363280370838897496675493411119896288413437648165762755516249385819799876190593410596588772953750592135480282406384799725149946020648503742074502433099373164782711911703171878502492156281762509092095744129027049665089185824440951849086836244861302746209634753793226318816715261107812607328110620115344977658157304987786773827545017316589662732722171023102044468910200415948303526753908200009932370035916020269727358967287596800092431351089975959771739670737705545635339787762830709652643567870591839666401171540256204228281060067944109179653941685365005272724324536997345115516175842141599701909755483073424634942570911097725362896370051831044608670285904915489785451861640485967121832187912603224446865674572002909630090732796419381087368300577008488549876326424201225775611351792112069706098584806375300143303206297900856011317453460216898549935534481242900706196770703583346771718929609650046081906453813744649270388930650195618060462577300950622064469234834931163196871839460688231399405614992896889553688544426015978917594885221304833320378882656155887963824087690654293193046677769081072048908640193133809524389871737237253005521747524415947922113766652805888438396054147567486987337167355166104543042899131993430820991650159647656208456324363081781712602217016347775564111441803592193088471289255795393642877824453654048165950323223788178060285905582700086738684883750337714980917164444693060308390225962379946272713289768929936724536553233482465794212734027070565388511936215762656298864684435370823571848084247138397525778310128785553445708026765901738206081873519050998499630965910793432366813853970202821394949406134489952453908830903545787795663963353463066176422602202105775621923781957494629716436815349210857072706316427328453577498513994637159418953720826767147133205092570099335672952611163628000805824409754601708517576411444079395720215248444116421555957828748745424226011844865906715664110802868290518210612860404454750511438922544439143868194881337928254493717003965533900634644833101037271446752357724260351165699515773967642913572637535873501670470613312527450847505213359793783861950188821929180325547521785176580032177426333023524832481849348447808439788218713542427529133485690077625718966956228062966168347006680212942597861705301218647978947398135250300005080179027045518797793505294699210802788329649160385012184940049568696199707027524548531041667993720684674742262714439058761479088895841824160373748908700112817860631624151944290572786670975635303518603594047587697599520048169851304461861034981185133385903366316421187501308774815870676228749311300865732973446153743975659991079304994892588234011350360387511421993302025047776808575499810068887160787732953050111241465547976334636931551115834357110038285979669707537501";

    @Test
    public void testFibonacciNaive() {
        assertEquals(new Integer(144), fibonacciNaive(11));
    }

    @Test
    public void testFibonacciWithAccumulators() {
        assertEquals(new Integer(144), fibonacciWithAccumulators(11));
    }

    @Test
    public void testBigFibonacciWithAccumulators() {
        try {
            final BigInteger actual = bigFibonacciWithAccumulators(new BigInteger(String.valueOf(100000)));
            assertTrue("bigFibonacciWithAccumulators should throw", false);
        } catch (Throwable t) {
            assertTrue(true);
        }
    }

    @Test
    public void testBigFibonacciTailCall() {
        assertEquals(new BigInteger(String.valueOf(this.fib_100000)),
                bigFibonacciTailCall(new BigInteger(String.valueOf(100000))));
    }
}
