create database bankingsystemapplication;

use bankingsystemapplication;

create table clientmaintable(
							AccountNumber bigint(10) primary key,
							ClientName varchar(50),
							AadhaarNumber bigint(10),
							Gender varchar(10),
							DateOfBirth varchar(10),
							MailID varchar(40),
							Password varchar(15),
							AccountCreationDateTime timestamp
                        );

create table clientaddresstable(
							AccountNumber bigint(10) primary key,
							Country varchar(20) ,
							HouseNumber varchar(10), 
							Pincode int(6) ,
							Street varchar(30), 
							AreaLocality varchar(30), 
							VillageTownCity varchar(30), 
							Landmark varchar(30) ,
							District varchar(30) ,
							State varchar(30),
                            foreign key(AccountNumber) references clientmaintable(AccountNumber)
						);



create table clientnomineetable(
							AccountNumber bigint(10) primary key, 
							NomineeName varchar(50) ,
							NomineeMobile bigint(10),
							NomineeAadhaar bigint(12),
							Profession varchar(25) ,
							Country varchar(25) ,
							Area varchar(25),
                            foreign key(AccountNumber) references clientmaintable(AccountNumber)
						);
                        
                        
create table bank(
				AccountNumber bigint(10) primary key, 
				Balance double(15,6) ,
				IFSC varchar(11) ,
				BranchName varchar(30),
                foreign key(AccountNumber) references clientmaintable(AccountNumber)
			);
            
insert into  clientmaintable 
	(AccountNumber, ClientName , AadhaarNumber,Gender , DateOfBirth, MailID ,Password, AccountCreationDateTime )
	values 
		(8332903671,"S.DURGA PRASANTH",321409873456,"Male","03-02-2002","prasanth6@gmail.com","Prasanth@6",CURRENT_TIMESTAMP),
        (8332903658,"S.RAVI KIRAN",567409873456,"Male","29-06-2002","kiran@gmail.com","kiran29",CURRENT_TIMESTAMP),
        (7993561848,"D. VENKATESH",621409873456,"Male","21-10-2002","venkymama@gmail.com","TDP",CURRENT_TIMESTAMP),
        (7671062715,"D. SANTHOSH KUMAR",451409873456,"Male","13-02-2002","santhosh@gmail.com","Santhu",CURRENT_TIMESTAMP),
        (8790982588,"B. SANDEEP YADAV",891409873456,"Male","14-07-2002","sandeep@gmail.com","sandeep123",CURRENT_TIMESTAMP),
		(9347660229,"L. SAI KUMAR",781409873456,"Male","26-01-2002","saikumar@gmail.com","TopperSai",CURRENT_TIMESTAMP),
        (9398989141,"S. MUNI TANUJ",931409873456,"Male","17-03-2002","munitanuj@gmail.com","masterchef",CURRENT_TIMESTAMP)
	;
    
select * from clientmaintable;

insert into  clientaddresstable 
	(AccountNumber, Country , HouseNumber,Pincode , Street, AreaLocality ,VillageTownCity, Landmark,District,State)
	values 
		(8332903671,"India","1-29",531133,"Dhucharapalem","G.K Veedhi","Dhucharapalem","near Government School","Vishakapatnam","Andhra Pradhesh"),
        (8332903658,"India","1-94",531133,"Dhucharapalem","G.K Veedhi","Dhucharapalem","near Government School","Vishakapatnam","Andhra Pradhesh"),
        (7993561848,"India","C13",535566,"Nellore","Pedapeta","Dhucharapalem","near Police station","Nellore","Andhra Pradhesh"),
        (7671062715,"India","B1-14",531100,"Marikavalasa","Pendhurthi","Marikavalasa","near Railway station","Srikakulam","Andhra Pradhesh"),
        (8790982588,"India","B-11",530033,"Pungunuru","Thirupathi","Pungunuru","near CMR shopping mall","Thrupathi","Andhra Pradhesh"),
       (9347660229,"India","7-29",531199,"Rinthada","Chithoor","Rinthada","near Government School","Chithoor","Andhra Pradhesh"),
        (9398989141,"India","1-00",631133,"Pungunuru","Thirupathi","Pungunuru","near Police Station","Thirupathi","Andhra Pradhesh")
	;

select * from clientaddresstable;

insert into  clientnomineetable 
	(AccountNumber, NomineeName , NomineeMobile,NomineeAadhaar , Profession, Country , Area)
	values 
		(8332903671,"S. SREE RAMULU",7332903670,321409873400,"Formar","India","Dhucharapalem"),
        (8332903658,"S. RAMA RAJU PADAL",9332903658,781409873456,"Teacher","India","Dhucharapalem"),
		(7993561848,"P. LOKESH",6732903658,661409873499,"Software","India","Nellore"), 
        (7671062715,"S. DURGA PARASANTH",8932903600,431409873478,"Developer","India","Marikavalasa"),
        (8790982588,"D. SAI ROHITH",8332903123,231409873477,"Hacker","India","Pungunuru"),
        (9347660229,"S. SAMIR YADAV",7832903658,421409873456,"Tester","India","Rinthada"),
        (9398989141,"S. MUNI THARUN",9832903699,661409873456,"Gammer","India","Pungunuru")
	;

select * from clientnomineetable;

insert into bank
			(AccountNumber,Balance,IFSC,BranchName)
            values
            (8332903671,200,"STUN0000091","AndhraPradhesh"),
			(8332903658,450,"STUN0000091","AndhraPradhesh"),
			(7993561848,1200,"STUN0000091","AndhraPradhesh"),
			(7671062715,3040,"STUN0000091","AndhraPradhesh"),
			(8790982588,155,"STUN0000091","AndhraPradhesh"),
			(9347660229,390,"STUN0000091","AndhraPradhesh"), 
			(9398989141,690,"STUN0000091","AndhraPradhesh")
            ;

select * from bank;
            
