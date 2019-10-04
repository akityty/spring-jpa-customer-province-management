package konkon.formatter;

import konkon.model.Province;
import konkon.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class ProvinceFormatter implements Formatter<Province> {

  private ProvinceService provinceService;

  @Autowired
  public ProvinceFormatter(ProvinceService provinceService) {
    this.provinceService = provinceService;
  }


  // Phương thức parse() chuyển một String sang kiểu dữ liệu đích sử dụng một Locale xác định
  @Override
  public Province parse(String text, Locale locale) throws ParseException {
    return provinceService.findById(Long.parseLong(text));
  }

  //  Phương thức print() chuyển đổi từ một đối tượng của kiểu dữ liệu đích sang String
  @Override
  public String print(Province object, Locale locale) {
    return "[" + object.getId() + ", " + object.getName() + "]";
  }


/*
  @Override
  public Province parse(String text, Locale locale) throws ParseException {
    return provinceService.findById(Long.parseLong(text));
  }

  @Override
  public String print(Province object, Locale locale) {
    return "[" + object.getId() + ", " +object.getName() + "]";
  }*/
}
