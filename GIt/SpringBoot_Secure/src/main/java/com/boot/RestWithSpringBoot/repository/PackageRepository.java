package com.boot.RestWithSpringBoot.repository;

import com.boot.RestWithSpringBoot.model.Package;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Repository
public class PackageRepository {

    
    private static List<Package> pkgList = new ArrayList<>();

    private static int pkgCount = 4;


    static {
        pkgList.add(new Package(1, "TEL", new Date(), "Iuu", "iit"));
        pkgList.add(new Package(2, "TCP", new Date(), "Cjj", "MIT"));
        pkgList.add(new Package(3, "BRO", new Date(), "Goi", "ghy"));
        pkgList.add(new Package(4, "WIN", new Date(), "soft", "iiu"));
    }

  
    public List<Package> findAll() {
        return pkgList;
    }

   
    public Package save(Package pkg) {
        if(pkg.getId()==null){
            pkg.setId(++pkgCount);
        }
        pkgList.add(pkg);
        return pkg;
    }

  
    public Package findById(int id) {
       for(Package pkg : pkgList) {
           if(pkg.getId() == id){
               return pkg;
           }
       }
       return null;
    }

    
    public Package deleteById(int id) {
        Iterator<Package> iterator = pkgList.iterator();
        while(iterator.hasNext()) {
            Package pkg = iterator.next();
            if(pkg.getId() == id) {
                iterator.remove();
                return pkg;
            }
        }
        return null;
    }
    
 
  	public void update(Package pkg) {
  		int index = pkgList.indexOf(pkg);
  		pkgList.set(index, pkg);
  	}

}
