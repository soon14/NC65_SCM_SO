package nc.vo.so.iufo;

import java.util.Map;

import nc.itf.scmpub.fetchfunc.IFuncParaValue;

public class UFOFuncParaValue implements IFuncParaValue {

  private UFOFuncParaVO ufoparavo;

  public UFOFuncParaValue(
      UFOFuncParaVO ufovo) {
    this.ufoparavo = ufovo;
  }

  @Override
  public String getParaValue(String parakey) {
    return (String) this.ufoparavo.getAttributeValue(parakey);
  }

  @Override
  public boolean isIncludeLower(String parakey) {
    Map<String, String[]> mapLower = this.ufoparavo.getLowerMap();
    return mapLower.containsKey(parakey);
  }

  @Override
  public String[] getSelfAndLowerValue(String parakey) {
    Map<String, String[]> mapLower = this.ufoparavo.getLowerMap();
    return mapLower.get(parakey);
  }

}
