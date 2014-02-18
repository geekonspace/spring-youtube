// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ve.gob.iribarren.tube.model;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ve.gob.iribarren.tube.model.ConfigLiveJwplayer;
import ve.gob.iribarren.tube.model.ConfigLiveJwplayerDataOnDemand;
import ve.gob.iribarren.tube.repository.ConfigLiveJwplayerRepository;

privileged aspect ConfigLiveJwplayerDataOnDemand_Roo_DataOnDemand {
    
    declare @type: ConfigLiveJwplayerDataOnDemand: @Component;
    
    private Random ConfigLiveJwplayerDataOnDemand.rnd = new SecureRandom();
    
    private List<ConfigLiveJwplayer> ConfigLiveJwplayerDataOnDemand.data;
    
    @Autowired
    ConfigLiveJwplayerRepository ConfigLiveJwplayerDataOnDemand.configLiveJwplayerRepository;
    
    public ConfigLiveJwplayer ConfigLiveJwplayerDataOnDemand.getNewTransientConfigLiveJwplayer(int index) {
        ConfigLiveJwplayer obj = new ConfigLiveJwplayer();
        setDescription(obj, index);
        setHeigth(obj, index);
        setStreamer(obj, index);
        setType(obj, index);
        setUrl(obj, index);
        setWidth(obj, index);
        return obj;
    }
    
    public void ConfigLiveJwplayerDataOnDemand.setDescription(ConfigLiveJwplayer obj, int index) {
        String description = "description_" + index;
        if (description.length() > 255) {
            description = description.substring(0, 255);
        }
        obj.setDescription(description);
    }
    
    public void ConfigLiveJwplayerDataOnDemand.setHeigth(ConfigLiveJwplayer obj, int index) {
        Integer heigth = new Integer(index);
        obj.setHeigth(heigth);
    }
    
    public void ConfigLiveJwplayerDataOnDemand.setStreamer(ConfigLiveJwplayer obj, int index) {
        String streamer = "streamer_" + index;
        if (streamer.length() > 45) {
            streamer = new Random().nextInt(10) + streamer.substring(1, 45);
        }
        obj.setStreamer(streamer);
    }
    
    public void ConfigLiveJwplayerDataOnDemand.setType(ConfigLiveJwplayer obj, int index) {
        String type = String.valueOf(index);
        if (type.length() > 1) {
            type = type.substring(0, 1);
        }
        obj.setType(type);
    }
    
    public void ConfigLiveJwplayerDataOnDemand.setUrl(ConfigLiveJwplayer obj, int index) {
        String url = "url_" + index;
        if (url.length() > 255) {
            url = new Random().nextInt(10) + url.substring(1, 255);
        }
        obj.setUrl(url);
    }
    
    public void ConfigLiveJwplayerDataOnDemand.setWidth(ConfigLiveJwplayer obj, int index) {
        Integer width = new Integer(index);
        obj.setWidth(width);
    }
    
    public ConfigLiveJwplayer ConfigLiveJwplayerDataOnDemand.getSpecificConfigLiveJwplayer(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        ConfigLiveJwplayer obj = data.get(index);
        Long id = obj.getId();
        return configLiveJwplayerRepository.findOne(id);
    }
    
    public ConfigLiveJwplayer ConfigLiveJwplayerDataOnDemand.getRandomConfigLiveJwplayer() {
        init();
        ConfigLiveJwplayer obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return configLiveJwplayerRepository.findOne(id);
    }
    
    public boolean ConfigLiveJwplayerDataOnDemand.modifyConfigLiveJwplayer(ConfigLiveJwplayer obj) {
        return false;
    }
    
    public void ConfigLiveJwplayerDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = configLiveJwplayerRepository.findAll(new org.springframework.data.domain.PageRequest(from / to, to)).getContent();
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'ConfigLiveJwplayer' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<ConfigLiveJwplayer>();
        for (int i = 0; i < 10; i++) {
            ConfigLiveJwplayer obj = getNewTransientConfigLiveJwplayer(i);
            try {
                configLiveJwplayerRepository.save(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            configLiveJwplayerRepository.flush();
            data.add(obj);
        }
    }
    
}