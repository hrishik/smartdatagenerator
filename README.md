# Smart Data Generator for Databases

The smart data generator helps you generate good quality data that for your relational databases can be configured through configuration files. The data generation mechanism can be extended to support different custom requirements. The configuration can be provided at runtime, please look at SmartDataClient/xml/smartdata-metadata-config.xml for an example. Currently the system supports Integer, Data, String and Double. However, it can be extended to support more complex types.The system generates data row by row in each iteration. It is a stateful system, so it remembers previous state, this helps the generator to generate different types of data based out of patterns. It provides following plugins by default :

##Fixed Data Plugin
Generates a fixed value for a given dataset, a table can be populated with fixed string, number or other data types. Below is sample configuration:

`<column name="segment_key" plugin-name="fixed" database-type="java.lang.String">
  <attribute name="fixed-value" value="CS"/>
</column>`

## Sequential Data Plugin
Allows users to specify range of numbers with increments. Same can happen with text as well.

`        <column name="erp" plugin-name="sequence" database-type="java.lang.Integer">
          <attribute name="from" value="1"/>
          <attribute name="to" value="43"/>
          <attribute name="increment-by" value="1"/>
          <attribute name="repeat-by" value="48"/>
        </column>`
##Multiple Choice Data Plugin
As name denotes, you can pick up multiple choices can be made available in the configuration, and system can randomly
 pick one choice.
 
 ##Random Data Plugin
 Enable users to pick up a data randomly from a given range. It also supports str-before, str-after to get combination of fixed text and random numbers in between. For e.g. year2001,year1995. Dates/Numbers can be picked up randomly as well, however one can provide a range as input configuration to control the scope.
 
 ## Pattern Data Plugin
 Generates data based out of example pattern provided as sample (as shown in below configuration). For example: you can provide a pattern of sine wave, and request to generate 1000 values cycle out of it. The pattern also takes a variance to bring some level of randomness between cycles. Below configuration shows how you can specify these patterns. This plugin primarily support integer, float and double data types.

 
 ` <column name="concentration" plugin-name="pattern" database-type="java.lang.Double">
          <attribute name="comma-separated-values"
                     value="0.129068005,0.129677993,0.130897969,0.179697013,0.179697013,0.216296296,0.301694624,0.637188053,1.247176105,2.589149821,2.46715221,2.589149821,2.467152,1,1.857164158,1.875463799,1.857164158,2.223156989,1.857164158,1.857164158,1.857164158,1.918162963,1.247176105,0.332194026,0.271195221"/>
          <attribute name="no-of-expected-values" value="48"/>
          <attribute name="variance-plus" value="10"/>
          <attribute name="variance-minus" value="10"/>
        </column>`
  
##Foreign Key Data Plugin
Many times, when you generate a dataset, you need that dataset to be a referential integrity coming from some master dataset/table.This plugin allows users to generate foreign key based constrainted data. In configuration, you can specify refTableName, refColumnName and refSchemaName or a SQL with sql return variable to consider for foreign key. This plugin supports string, integer etc.

##Script Data Plugin
The script Data plugin enables users to pass dynamic javascript based information through its plugin. You can specify the script in configuration through attribute `expression` which can be evaluated at every row execution, and the generated value can be passed. This is useful to pass current time, or some values that are getting generated in real time.

#Configuration
The generator requires smartdata-metadata-config.xml file which contains information in following order :
`- <smartdata-metadata>
  - <schema> - 1 or many
      - <table> - 1 or manyou ma
          - <column> - 1 or many
              - Information that needs to go in`

Each schema entry requires a database access password. Once the configuration is created for required tables, you can call the manager to add entries with following code snippet :
`
    ObjectFactory objFact = ObjectFactory.getInstance();
    SmartDataLoadManager manager =
      objFact.getDataLoadManager("<location>/smartdata-metadata-config.xml");
    for (int i=0;i<10;i++) //only adding 10 rows
      manager.next();
    manager.close();
`
Please note that you need to have respective JDBC driver in library path. You also need to remember that this is maven project, you may need to install jta and jdbc-stdext explictly in your local nexus/maven repository as they are missing from maven2 repo. This can be done by downloading respective jar files and running following CLI:
`
mvn install:install-file -DgroupId=javax.transaction -DartifactId=jta -Dversion=1.0.1B -Dpackaging=jar -Dfile=jta-1.0.1b.jar
mvn install:install-file -DgroupId=javax.sql -DartifactId=jdbc-stdext -Dversion=2.0 -Dpackaging=jar -Dfile=jdbc-stdext.jar
`


#How to create your own plugin

Each plugin has META-INF/smartdata-plugin-config.xml packaged in jar file. Once the jar is kept in classpath, the system automatically reads the file to load the respective plugin. An example smartdata-plugin-config.xml is shown below :
`<?xml version="1.0" encoding="UTF-8" ?>
<smartdata-plugin-config xmlns="http://www.hk.com/xsds/smartdata" name="pattern" description="Pattern Data Plugin"
                         plugin-class="org.hk.smartdata.plugin.pattern.impl.PatternDataPluginManager">
  <attribute name="comma-separated-values"></attribute>
  <attribute name="no-of-expected-values"></attribute>  
  <attribute name="variance-plus"></attribute>  
  <attribute name="variance-minus"></attribute>  
</smartdata-plugin-config>`

Typically, it would contain list of supported attributes, name of plugin, description, and the manager class for it. The attributes defined in this file, will go as attribute name, values in smartdata-metadata-config.xml.


