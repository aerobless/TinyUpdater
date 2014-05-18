TinyUpdater
===========

TinyUpdater is a lightweight, standalone java application intended for updating other java applications. It has both a command-line and GUI mode. The way it works is that you call TinyUpdater with the URL to the updated jar (for example on a Jenkins server). TinyUpdater will download that jar while displaying a progress indication for the enduser. Once the file has been successfully downloaded, TinyUpdater will overwrite the old .jar with the new one.

Integrating TinyUpdater into your application is easy. Download TinyUpdater on your endusers computer or ship it with your application. To run an update all you do is create a new executor and specify the correct parameters.

  	try {
		Runtime.getRuntime().exec("java -jar "+downloadPath+" 10 "+updateURL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
##Parameters

| Parameter | Description | Example |
| --------- | ----------- | ------- |
| arg0      | The time TinyUpdater should wait before attempting to overwrite your original .jar in seconds. | 10
| arg1    | The URL to your updated .jar file. | http:// yourServer.com/yourApp.jar
| arg2 (optional) | The title of your application. (If you specify arg2 we assume that you want to run the GUI version. If you don't specify arg2 we'll run the CLi version instead. |  Your Application Name

**Difference between GUI-Mode and CLI-Mode:**  
If you specify a application title as arg2 we'll launch the GUI mode. The GUI version will display a nice progressbar while downloading the new .jar. It will also lauch the new .jar once it is finished downloading it. (The CLI-version will not launch the downloaded .jar because often you'd want to launch CLI apps with special args or from a .sh or .bat launcher.)

![GUI Screenshot](https://raw.githubusercontent.com/aerobless/TinyUpdater/master/screenshot_gui.png)

**TinyUpdater CI-Server:** [http://w1nter.net:8080/job/TinyUpdater/](http://w1nter.net:8080/job/TinyUpdater/)  
**TinyUpdater Sonar-Server:** [http://sonar.w1nter.net/dashboard/index/103](http://sonar.w1nter.net/dashboard/index/103)


##Licence

> Copyright (c) 2014 Theo Winter

> Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

> The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

> THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
