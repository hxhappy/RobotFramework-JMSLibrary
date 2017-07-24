# RobotFramework-JMSLibrary
Robot Framework Java library for testing JMS applications. Only Support TibcoEMS now
## Use
1. Add JAR to system path: JMSLibrary/out/artifacts/JMSLibrary.jar.
2. Add library XML to python home: JMSLibrary/src/Message/JMSLibrary.xml.
3. Import JMSLibrary in RobotFramework, eg: Message.TibcoEMS
4. Connect TibcoEMS eg: Connect Server	${EMSServerURL}	${EMSUserName}	${EMSPassword}
