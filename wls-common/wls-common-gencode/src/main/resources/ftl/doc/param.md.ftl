### ${entityName}-对象

|  参数名称         |   参数类型     |   是否必传    |  参数说明        |
|  :----           |  :-----       |  :-----      |  -----          |
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list fields as field>
|  ${field.propertyName}     |  ${field.propertyType}  |  ${field.required}     |  ${field.comment}   |
</#list>
<#------------  END 字段循环遍历  ---------->


