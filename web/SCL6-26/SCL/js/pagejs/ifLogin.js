var Yewuyuan;
Yewuyuan = $.cookie("Yewuyuan");
if(!Yewuyuan){
     window.location.href = "login.html";
    $('.userName').text('');
   }else{
     $('.userName').text(Yewuyuan);
   }