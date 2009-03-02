answer()

wait(1000) // wait for 1 second
log("this is a log demo")
currentCall.log("Incoming call info [state:" + currentCall.state()  + ",callerID:"+ currentCall.callerID + ",callleeId:" + currentCall.calleeId
	+ ",callerName:" + currentCall.callerName + ",calleeName:" + currentCall.calleeId)

def event=prompt("http://127.0.0.1:8080/beep.wav where are you heading?",
   [repeat:3,record:true, beep:true, silenceTimeout: 3, maxTime:30,timeout:20.03456789,
    onRecord: {event-> say("you said " + event.recordURI );},
    choices:"1st Floor (first, house wares, 1),\n 2nd Floor (second, bed and bath, 2),\n 3rd Floor (third, sporting goods, 3)", 
    onChoice: {event->
      event.onChoice( "1st Floor", { say("Your destination is 1st Floor") } );
      event.onChoice( "2nd Floor", { say("Your destination is 2nd Floor") } );
      event.onChoice( "3rd Floor", { say("Your destination is 3rd Floor") } ); 
    }, 
    onBadChoice: { say("I can not recognize you. Please input again."); }, 
    onTimeout: { say("wait input time out"); }, 
    onHangup: { print("Disconnected by the peer!<<<<<<<<<<<<<<<<<"); }, 
    onError: { say("You've get an error!"); },
    onEvent: {event->
      if(event.name!="hangup"){ say("inner callback got triggered by event " + event.name);}
      event.onError( { say("000You've got an error err! ") } );
      event.onTimeout( { say("000wait input time out") } );
      event.onHangup( { print("000Discnnected by the peer!<<<<<<<<<<<<<<<<<") } );
      event.onChoice( "1st Floor", { say("Your destination is 1st Floor") } );
      event.onChoice( "2nd Floor", { say("Your destination is 2nd Floor") } );
      event.onChoice( "3rd Floor", { say("Your destination is 3rd Floor") } ); 
      event.onBadChoice( { say("I can not recognize you. Please input again. ") } ); 
      event.onRecord( {recordEvent-> say("you said " + recordEvent.recordURI );});
    }
  ]
);

if(event.name!="hangup"){
  say("run outer call back for event [" + event.name +"," + event.value +"]");
  event.onError( { say("You've got an error err! ") } );
  event.onTimeout( { say("wait input time out") } );
  event.onChoice( "1st Floor", { say("Your destination is 1st Floor") } );
  event.onChoice( "2nd Floor", { say("Your destination is 2nd Floor") } );
  event.onChoice( "3rd Floor", { say("Your destination is 3rd Floor") } ); 
  event.onBadChoice( { say("I can not recognize you") } ); 
  event.onRecord( {recordEvent-> say("you said " + recordEvent.recordURI );});
  say("Thanks for testing Java Script on the Tropo platform");
  hangup()
}
else{
  print(">>>>>>>>>>>>>>>Discnnected by the peer!<<<<<<<<<<<<<<<<<");
}