var addressInit = function(_cmbProvince, _cmbCity, _cmbArea, defaultProvince, defaultCity, defaultArea)  
{  
    var cmbProvince = document.getElementById(_cmbProvince);  
    var cmbCity = document.getElementById(_cmbCity);  
    var cmbArea = document.getElementById(_cmbArea);  
      
    function cmbSelect(cmb, str)  
    {  
        for(var i=0; i<cmb.options.length; i++)  
        {  
            if(cmb.options[i].value == str)  
            {  
                cmb.selectedIndex = i;  
                return;  
            }  
        }  
    }  
    function cmbAddOption(cmb, str, obj)  
    {  
        var option = document.createElement("OPTION");  
        cmb.options.add(option);  
        option.innerText = str;  
        option.value = str;  
        option.obj = obj;  
    }  
      
    function changeCity()  
    {  
        cmbArea.options.length = 0;  
        if(cmbCity.selectedIndex == -1)return;  
        var item = cmbCity.options[cmbCity.selectedIndex].obj;  
        for(var i=0; i<item.areaList.length; i++)  
        {  
            cmbAddOption(cmbArea, item.areaList[i], null);  
        }  
        cmbSelect(cmbArea, defaultArea);  
    }  
    function changeProvince()  
    {  
        cmbCity.options.length = 0;  
        cmbCity.onchange = null;  
        if(cmbProvince.selectedIndex == -1)return;  
        var item = cmbProvince.options[cmbProvince.selectedIndex].obj;  
        for(var i=0; i<item.cityList.length; i++)  
        {  
            cmbAddOption(cmbCity, item.cityList[i].name, item.cityList[i]);  
        }  
        cmbSelect(cmbCity, defaultCity);  
        changeCity();  
        cmbCity.onchange = changeCity;  
    }  
      
    for(var i=0; i<provinceList.length; i++)  
    {  
        cmbAddOption(cmbProvince, provinceList[i].name, provinceList[i]);  
    }  
    cmbSelect(cmbProvince, defaultProvince);  
    changeProvince();  
    cmbProvince.onchange = changeProvince;  
}  
  
var provinceList = [  
 
{name:'河北', cityList:[           
{name:'石家庄市', areaList:['市辖区','长安区','桥东区','桥西区','新华区','井陉矿区','裕华区','开发区','井陉县','正定县','栾城县','行唐县','灵寿县','高邑县','深泽县','赞皇县','无极县','平山县','元氏县','赵　县','辛集市','藁城市','晋州市','新乐市','鹿泉市']},          
{name:'唐山市', areaList:['市辖区','路南区','路北区','古冶区','开平区','丰南区','丰润区','开发区','滦　县','滦南县','乐亭县','迁西县','玉田县','唐海县','遵化市','迁安市']},          
{name:'秦皇岛市', areaList:['市辖区','海港区','山海关区','北戴河区','开发区','青龙满族自治县','昌黎县','抚宁县','卢龙县']},             
{name:'邯郸市', areaList:['市辖区','邯山区','丛台区','复兴区','峰峰矿区','开发区','邯郸县','临漳县','成安县','大名县','涉　县','磁　县','肥乡县','永年县','邱　县','鸡泽县','广平县','馆陶县','魏　县','曲周县','武安市']},           
{name:'邢台市', areaList:['市辖区','桥东区','桥西区','开发区','邢台县','临城县','内丘县','柏乡县','隆尧县','任　县','南和县','宁晋县','巨鹿县','新河县','广宗县','平乡县','威　县','清河县','临西县','南宫市','沙河市']},            
{name:'保定市', areaList:['市辖区','新市区','北市区','开发区','南市区','满城县','清苑县','涞水县','阜平县','徐水县','定兴县','唐　县','高阳县','容城县','涞源县','望都县','安新县','易　县','曲阳县','蠡　县','顺平县','博野县','雄　县','涿州市','定州市','安国市','高碑店市']},           
{name:'张家口市', areaList:['市辖区','桥东区','桥西区','宣化区','下花园区','开发区','宣化县','张北县','康保县','沽源县','尚义县','蔚　县','阳原县','怀安县','万全县','怀来县','涿鹿县','赤城县','崇礼县']},          
{name:'承德市', areaList:['市辖区','双桥区','双滦区','鹰手营子矿区','开发区','承德县','兴隆县','平泉县','滦平县','隆化县','丰宁满族自治县','宽城满族自治县','围场满族蒙古族自治县']},          
{name:'沧州市', areaList:['市辖区','新华区','运河区','开发区','沧　县','青　县','东光县','海兴县','盐山县','肃宁县','南皮县','吴桥县','献　县','孟村回族自治县','泊头市','任丘市','黄骅市','河间市']},          
{name:'廊坊市', areaList:['市辖区','安次区','广阳区','开发区','固安县','永清县','香河县','大城县','文安县','大厂回族自治县','霸州市','三河市']},          
{name:'衡水市', areaList:['市辖区','桃城区','开发区','枣强县','武邑县','武强县','饶阳县','安平县','故城县','景　县','阜城县','冀州市','深州市']}  
]}
 
];  