package com.egc.baseapi.util

class MyObjectMapper{
    static <T>T convert(def src, Class<T> aClass){
        if (src==null) return null
        T result=aClass.getDeclaredConstructor().newInstance()
        src.properties.each {prop,val->
            if (result.hasProperty(prop)&&prop!='class') {
                result[prop]=val
            }
        }
        return  result
    }

    static <T,D>D merge(def dst, Class<D> dClass,def src, Class<T> aClass){
        if (src==null)return null
        src.properties.each {prop,val->
            if (dst.hasProperty(prop)&&prop!='class')
                dst[prop]=val
        }
        return dst
    }


    static <T>T ConvertByRemoveItems(def src, Class<T> aClass, List<String> items){
        if (src==null) return null
        T result=aClass.getDeclaredConstructor().newInstance()
        src.properties.each { prop, val ->
            if (result.hasProperty(prop as String) && prop != 'class' && items.contains(prop))
                result[prop as String] = val
        }
        return result
    }

    static <T>T convertfromFormDataMap(Map src, Class<T> aClass){
        if (src==null)return  null
        T result=aClass.getDeclaredConstructor().newInstance()
        try {
            src.each{prop,val->
                try{
                    if (result.class.getDeclaredField(prop).type==java.lang.Long.class){
                        result[prop]=Long.valueOf(val[0])
                    }else if(result.class.getDeclaredField(prop).type==java.lang.Interger.class){
                        result[prop]=Interger.valueOf(val[0])
                    }else if(result.class.getDeclaredField(prop).type==int.class){
                        result[prop]=Interger.valueOf(val[0])
                    }else  result[prop]=val[0]
                }catch (e){
                    print(prop)
                }
            }
        }catch(e){
            print(e)
        }
        return result
    }


    static <T>T convertfromMap(Map src, Class<T> aClass){
        if (src==null) return null
        T result=aClass.getDeclaredConstructor().newInstance()
        try{
            src.each {prop,val->
                try{
                    print(val)
                    result[prop]=val
                }catch (e){
                    print(prop)
                }
            }
        }catch (e){
            print(e)
        }
        return result
    }



    static void convert(def src,def dst){
        if (src==null){
            dst=null
            return
        }
        src.properties.each {prop,val->
            if (dst.hasProperty(prop)&&prop!='class')
                dst[prop]=val
        }
    }

    static <T>List<T> convert(List src, Class<T> aClass){
        def result=new ArrayList<T>()
        for (Object it:src){
            def temp=aClass.getDeclaredConstructor().newInstance()
            convert(it,temp)
            result.add(temp)
        }
        return result
    }


}