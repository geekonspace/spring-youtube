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
import ve.gob.iribarren.tube.model.Category;
import ve.gob.iribarren.tube.model.CategoryDataOnDemand;
import ve.gob.iribarren.tube.repository.CategoryRepository;

privileged aspect CategoryDataOnDemand_Roo_DataOnDemand {
    
    declare @type: CategoryDataOnDemand: @Component;
    
    private Random CategoryDataOnDemand.rnd = new SecureRandom();
    
    private List<Category> CategoryDataOnDemand.data;
    
    @Autowired
    CategoryRepository CategoryDataOnDemand.categoryRepository;
    
    public Category CategoryDataOnDemand.getNewTransientCategory(int index) {
        Category obj = new Category();
        setName(obj, index);
        return obj;
    }
    
    public void CategoryDataOnDemand.setName(Category obj, int index) {
        String name = "name_" + index;
        if (name.length() > 45) {
            name = new Random().nextInt(10) + name.substring(1, 45);
        }
        obj.setName(name);
    }
    
    public Category CategoryDataOnDemand.getSpecificCategory(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Category obj = data.get(index);
        Long id = obj.getId();
        return categoryRepository.findOne(id);
    }
    
    public Category CategoryDataOnDemand.getRandomCategory() {
        init();
        Category obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return categoryRepository.findOne(id);
    }
    
    public boolean CategoryDataOnDemand.modifyCategory(Category obj) {
        return false;
    }
    
    public void CategoryDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = categoryRepository.findAll(new org.springframework.data.domain.PageRequest(from / to, to)).getContent();
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Category' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Category>();
        for (int i = 0; i < 10; i++) {
            Category obj = getNewTransientCategory(i);
            try {
                categoryRepository.save(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            categoryRepository.flush();
            data.add(obj);
        }
    }
    
}
