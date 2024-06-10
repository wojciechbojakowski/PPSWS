; -- Example1.iss --
; Demonstrates copying 3 files and creating an icon.

; SEE THE DOCUMENTATION FOR DETAILS ON CREATING .ISS SCRIPT FILES!

[Setup]
AppName=PPSWS
AppVersion=1.0
WizardStyle=modern
DefaultDirName={autopf}\PPSWS
DefaultGroupName=PPSWS
UninstallDisplayIcon={app}\PPSWS.exe
Compression=lzma2
SolidCompression=yes
OutputDir=userdocs:PPSWS

[Files]
Source: "TEST_JAR.jar"; DestDir: "{app}\Source"
Source: "README.md"; DestDir: "{app}"; Flags: isreadme

[Icons]
Name: "{group}\My Java Application"; Filename: "{app}\jre\bin\javaw.exe"; Parameters: "-jar ""{app}\app.jar"""

[Run]
Filename: "javaw.exe"; Parameters: "-jar ""{app}\Source\TEST_JAR.jar"""; Description: "{cm:LaunchProgram,PPSWS}"; Flags: nowait postinstall skipifsilent