package org.hk.smartdata.plugin.pattern.impl;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

import org.hk.smartdata.framework.model.SmartDataGenConstants;
import org.hk.smartdata.framework.plugin.SmartDataElement;

public class PatternDataPluginElement
  extends SmartDataElement
{
  private static final String COMMA_SEPARATED_VALUES = "comma-separated-values";
  private static final String EXPECTED_VALUES = "no-of-expected-values";

private static final String VARIANCE_PLUS = "variance-plus";
  private static final String VARIANCE_MINUS = "variance-minus";

  private int expectedPoints = 100;
  private double varianceplus = 5;
  private double varianceminus = 5;
  List<Double> samplePattern;
  
  private double[] inpoints;
  private double[] outpoints;
  private int[] roundedinpoints;
  private double lastNo;
  private int randFrom, randTo, randDiff;
  private Random r;
  private Queue<Double> qElement;
    
  public PatternDataPluginElement(Class clazz, String string, Map<String, String> map)
  {
    super(clazz, string, map);
    if (isInteger(clazz) || isDouble(clazz) || isFloat(clazz))
    {
      expectedPoints = getIntValueFromStr(map.get(EXPECTED_VALUES));
      varianceplus = getDoubleValueFromStr(map.get(VARIANCE_PLUS));
      varianceminus = getDoubleValueFromStr(map.get(VARIANCE_MINUS));
      qElement = new LinkedList<Double>();

      samplePattern = new ArrayList<Double>();
      String values = map.get(COMMA_SEPARATED_VALUES);
      for (String value: values.split(SmartDataGenConstants.COMMA))
      {
        samplePattern.add(new Double(value));
      }
      qElement = new LinkedList<Double>();    
      initialize();
    }
  }

  @Override
  public String nextDouble()
  {
    //return nextValue().toString();
    Double val = nextValue();
    return val.toString();
  }
  @Override
  public String nextInt()
  {
    return nextValue().intValue() + "";
  }
  @Override
  public String nextFloat()
  {
    return nextValue().floatValue() + "";
  }

  private void initialize() 
  {
    inpoints = new double[samplePattern.size() + 1];    
    for (int i = 0; i < samplePattern.size(); i++) {
        inpoints[i] = samplePattern.get(i);
    }
    inpoints[samplePattern.size()] = inpoints[0];
    int count = samplePattern.size() + 1;
    int expectedPointsPerCycle = expectedPoints;
    double ratio = ((double)expectedPointsPerCycle) / ((double)samplePattern.size());
    outpoints = new double[expectedPoints];
    roundedinpoints = getRoundedInPoints(count, ratio, expectedPointsPerCycle);
    
    lastNo = inpoints[0];
    randFrom = (int) (100 - varianceminus);
    randTo = (int) (100 + varianceplus);
    randDiff = randTo - randFrom;    
    r = new Random();        
  }

  private Double nextValue()
  {
    if (qElement.isEmpty())
    {
      int j = 0;
      for (int k = 0; k < samplePattern.size(); k++)
      {
        double from = inpoints[k];
        double to = inpoints[k + 1];
        double diff = (to - from) / roundedinpoints[k];
        for (int l = 0; l < roundedinpoints[k]; l++)
        {
          lastNo += diff;
          double randNo = randFrom + randDiff * r.nextInt(100) / 100;
          outpoints[j++] = (lastNo * randNo / 100);
          qElement.add(outpoints[j - 1]);
        }
      }
      
    }
    return qElement.remove();
  }
  
  private int[] getRoundedInPoints(int count, double ratio, int expectedPoints)
  {
    int[] roundedinpoints = new int[count];
    //first round off
    int lastPoint = 0;
    float totalFlt = 0;
    int total = 0;
    int sumOfRound = 0;
    for (int i = 0; i < count; i++)
    {
      totalFlt += ratio;
      total = Math.round(totalFlt);
      roundedinpoints[i] = total - lastPoint;
      if ((count - 1) != i)
        sumOfRound += roundedinpoints[i];
      lastPoint = total;
    }
    if (sumOfRound < expectedPoints)
    {
      roundedinpoints[count]++;
    }
    return roundedinpoints;
  }

}
