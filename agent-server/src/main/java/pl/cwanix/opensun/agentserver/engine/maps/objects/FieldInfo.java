package pl.cwanix.opensun.agentserver.engine.maps.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import pl.cwanix.opensun.agentserver.engine.maps.structures.FieldInfoStructure;
import pl.cwanix.opensun.utils.datatypes.Vector;
import pl.cwanix.opensun.utils.files.SUNArchive;
import pl.cwanix.opensun.utils.files.SUNArchiveChunkInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("checkstyle:MagicNumber")
@Getter
@Setter
@Slf4j
public class FieldInfo {

    private static final Marker MARKER = MarkerFactory.getMarker("FIELD INFO");

    private final FieldInfoStructure fieldInfoStructure;

    private boolean viewport;
    private Vector startingVector;
    private int sectorSize;
    private int sectorXNum;
    private int sectorYNum;
    private int totalSectorNum;
    private int shiftMinX;
    private int shiftMinY;
    private int shiftMaxX;
    private int shiftMaxY;
    private WorldBase worldBase;
    private SectorInfo sectorInfo;
    private TriggerGroupInfo triggerGroupInfo;
    private Map<Integer, MapObjectInfo> mapObjectInfoMap;
    private List<Integer> startTilesList;

    public FieldInfo(FieldInfoStructure fieldInfoStructure) {
        this.fieldInfoStructure = fieldInfoStructure;
        this.mapObjectInfoMap = new HashMap<>();
        this.startTilesList = new ArrayList<>();
        this.triggerGroupInfo = new TriggerGroupInfo();

        this.worldBase = new WorldBase(); //create?
    }

    public void load(String dataDirectory) {
        SUNArchive archive = new SUNArchive();

        if (!archive.loadFile(dataDirectory + "/" + fieldInfoStructure.getPath())) {
            log.error(MARKER, "Unable to load field file: {}", fieldInfoStructure.getPath());
        }

        if (!worldBase.serialize(archive)) {
            log.error(MARKER, "Unable to serialize world base");

            throw new RuntimeException("");
        }

        loadMapObjectInfo(archive);
    }

    private void loadMapObjectInfo(SUNArchive archive) {
        boolean areaChunk = false;

        while (true) {
            SUNArchiveChunkInfo chunkInfo = archive.readChunk();

            if (chunkInfo.getId() == 0x1781) {
                areaChunk = true;
                break;
            }

            archive.skipCurrentChunk(chunkInfo);
        }

        int numbers = 0;
        int dummy = 0;
        int[] custom = new int[4];

        //numbers = archive read int bytes
        for (int i = 0; i < numbers; i++) {
            String name = archive.readName();
            //dummy = archive read int bytes
        }

        //numbers = archive read int bytes
        for (int i = 0; i < numbers; i++) {
            //dummy = archive read int bytes
            String name = archive.readName();
        }

        //numbers = archive read int bytes
        for (int i = 0; i < numbers; i++) {
            MapObjectInfo object = new MapObjectInfo();
            object.setMapObjectInfoId(i);
            //object.setId(new SUNId( archive read int ));
            //object.setAttribute( archive read int );
            //object.setPos( archive read int );
            //object.setRot( archive read int );
            //object.setScale( archive read int );
            //object.setBoundingVolume( archive read ? );

            if (archive.getVersion() >= 138) {
                //dummy = archive read int bytes
            }

            if (archive.getVersion() >= 158) {
                //custom[0] = archive read int bytes
                //custom[1] = archive read int bytes
                //custom[2] = archive read int bytes
                //custom[3] = archive read int bytes
            }

            mapObjectInfoMap.put(object.getMapObjectInfoId(), object);
        }
    }

    public void establishSectorInfo(final int sectorSize, final boolean viewport) {
    }
}
