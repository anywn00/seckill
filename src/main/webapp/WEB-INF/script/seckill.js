/**
 * Created by 第九 on 2017/9/13.
 */
var seckill = {
    URL : {
      nowTime : function(){
          return "/seckill//time/now";
      },
      exporter : function(seckillId){
          return  "/seckill/" + seckillId + "/exporser" ;
      },
      execution : function(seckillId,md5){
          return "/seckill/" + seckillId + "/" + md5 + "/execution"
      }
    },
    validatePhone : function (phone) {
        if(phone && phone.length == 11 && !isNaN(phone)){
            return true;
        }
        return false;
    },
    handlerSeckillkill : function(seckillId,node){
        node.hide()
            .html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.poster(seckill.URL.exporter(),{},function(result){
            if(result && result['success']){
                var exporser = result['data'];
                if(exporser['exposed']){
                    var md5 = exporser['md5'],
                    execution = seckill.URL.execution();
                    $.post(execution,{},function(result){
                        if(result && result['success']){
                            var killResult  = result['data'],
                                stateInfo   = result['stateInfo'];
                            node.html('<span class="label label-success">'+stateInfo+'</span>')
                        }
                    })
                }else{
                    //秒杀未开启
                    seckill.countdown(seckillId,exporser['start'],exporser['end'],exporser['now']);
                }
            }else{
                console.log(result);
            }
        })
    },
    countdown : function(seckillId,startTime,endTime,now){
        var seckillBox = $('#seckill-box');
        if(now < startTime){
            //秒杀未开始
            //显示计时面板
            var killTime = new Data(startTime + 1000);//秒杀时间
            seckillBox.countdown(killTime,function(event){
                //时间格式
                var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒')
                seckillBox.html(format)
            })//时间完成后回掉时间
                .on('finish.countdown',function(){
                    seckill.handlerSeckillkill(seckillId,seckillBox);
                })

        }else if(now > endTime){
            //秒杀结束
            seckillBox.html('秒杀结束');
        }else{
            //秒杀开始
            seckill.handlerSeckillkill(seckillId,seckillBox);
        }
    },
    detail : {
        init : function(params){

            var killPhone = $.cookie("killPhone");

            if(!seckill.validatePhone(killPhone)){
                //绑定手机号
                //控制输出
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    show : true,
                    backdrop:'static',
                    keyboard:false
                });
                $('#killPhoneBtn').click(function(){
                    var killPhoneKey = $('#killPhoneKey').val();
                    console.log(killPhoneKey); //TODO
                    if(seckill.validatePhone(killPhoneKey)){
                        //手机号写入cookie中
                        $.cookie('killPhone',killPhoneKey,{expires:7,path:'/seckill'});
                        //刷新页面
                        window.location.reload();
                    }else{
                        //输入错误
                        $('#killPhoneMessage').hide().html('<label class="label label-dannager">手机号错误</label>').show()
                    }
                });
            }else{
                //登陆进去了
                //计时面板
                var startTime   = params['startTime'],
                    endTime     = params['endTime'],
                    seckillId   = params['seckillId'];
                $.get(seckill.URL.nowTime(),{},function(result){
                    if(result && result['success']){
                        var now = result['data'];
                        seckill.countdown(seckillId,startTime,endTime,now);
                    }else{
                        console.log(result) //TODO
                    }
                })
            }

        }
    }
}