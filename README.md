TinyUpdater
===========

In some operating systems, for example Windows, you can't overwrite .jar files while they're being executed. Therefor you need a middle-man that handles downloading & overwriting the original .jar with a updated version. You could use batch & shell scripts for this task, but then you have to worry about operating system again.

With TinyUpdater you can work around this issue. Just have your application download "TinyUpdater" and run it through an executor before closing your main application.

  try {
			Runtime.getRuntime().exec("java -jar "+downloadPath+" 10 "+updateURL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
you can specify a waitTime in seconds and a updateURL where the updated .jar of your application can be found.

**TinyUpdater CI-Server:** [http://w1nter.net:8080/job/TinyUpdater/](http://w1nter.net:8080/job/TinyUpdater/)

Copyright (c) 2014 Theo Winter

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
