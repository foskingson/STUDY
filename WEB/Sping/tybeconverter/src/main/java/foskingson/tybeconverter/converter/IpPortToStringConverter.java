package foskingson.tybeconverter.converter;



import org.springframework.core.convert.converter.Converter;

import foskingson.tybeconverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IpPortToStringConverter implements Converter<IpPort,String> {
    @Override
    public String convert(IpPort source) {
        log.info("convert Source= {}",source);
        
        return source.getIp()+":"+source.getPort();
    }
    
}
