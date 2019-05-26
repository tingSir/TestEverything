package com.resoft;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {


    public static void main(String[] args) {

//        //string regexstr = @"<[^>]*>";    //去除所有的标签
//
//        //@"<script[^>]*?>.*?</script>" //去除所有脚本，中间部分也删除
//
//        // string regexstr = @"<img[^>]*>";   //去除图片的正则
//
//        // string regexstr = @"<(?!br).*?>";   //去除所有标签，只剩br
//
//        // string regexstr = @"<table[^>]*?>.*?</table>";   //去除table里面的所有内容
//
//        String regexstr = @"<(?!img|br|p|/p).*?>";   //去除所有标签，只剩img,br,p
//        str = Regex.Replace(str, regexstr, string.Empty, RegexOptions.IgnoreCase);


//        new test().testThread();

        /**
         *    Map<String,Object> map = new HashMap<String,Object>();
         *   map.put("id", t.getId());
         *   map.put("name", t.getName());
         *   map.put("principal", t.getPrincipal());
         *   map.put("teamer_id", t.getTeamerId());
         *   map.put("create_by", t.getCreateBy());
         *   map.put("status", t.getStatus());
         *   map.put("is_private", t.getIsPrivate());
         *   map.put("create_date", t.getCreateDate());
         *   map.put("update_date", t.getUpdateDate());
         *   map.put("description", t.getDescription());
         *   map.put("type", t.getType());
         *   map.put("parent_ids", t.getParentIds());
         *   map.put("parent_id", t.getParentId());
         *   map.put("over_output", t.getOverOutput());
         *   map.put("start_input", t.getStartInput());
         *   map.put("flow_id", t.getFlowId());
         *   map.put("extend_ids", t.getExtendIds());
         *   map.put("order_number", t.getOrderNumber());
         */
        try {

            String path = "J:\\workspaceYL\\mvs-main\\mvs-core-service\\src\\main\\java\\com\\mvs\\modular\\persistence\\dao\\mapping\\UserMapper.xml";
                String string=FileUtils.readFileToString(new File(path),"utf-8");
                XMLSerializer xmlSerializer = new XMLSerializer();
                JSONObject read = (JSONObject) xmlSerializer.read(string);
                JSONArray jsonArray = (JSONArray) ((JSONObject) read.get("resultMap")).get("result");
            /**
             * @column
             * @property
             */
            System.out.println("private Map<String,Object> populationMap(RobotTaskTemplateEntity t){");
            System.out.println("Map<String,Object> map = new HashMap<String,Object>();");
            for(Object obj:jsonArray){
                String name= (String)((JSONObject)obj).get("@column");
                String str = (String)((JSONObject)obj).get("@property");
                str=str.replace("is","");
                String property= str.substring(0, 1).toUpperCase() + str.substring(1);
                System.out.println("map.put(\""+name+"\", "+"t.get"+property+"());");
                }
            System.out.println("\t\tMapUtils.removeNullValue(map);\n");
            System.out.println("\t\treturn map;\n}");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void seekWechatUserPic() {
//        System.setProperty("webdriver.gecko.driver","D:\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "D:\\test\\chromedriver.exe");
        String downloadFilepath = "J:\\workspaceYL\\mvs-bot\\jeecg-framework\\target\\jeecg\\upload\\wechatUserIcon";
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--test-type");
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        WebDriver driver = new ChromeDriver(cap);
        WebDriver.Navigation navigation = driver.navigate();
        //指定登陆页面
        String path = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxnewloginpage?ticket=Af9ROHYlF_E7iy3dLxdYW9Me@qrticket_0&uuid=Qbng1rQqsw==&lang=zh_CN&scan=1539053455";
        driver.get(path);
        try {
            /**
             * 下面通过元素选择器对获取到的页面进行字段抽取，遍历打印出需要的数据。
             */
            List<WebElement> elements = driver.findElements(By.className("header"));
            List<WebElement> avatar = ((RemoteWebElement) elements.get(0)).findElementsByTagName("img");
            String imgSrc = avatar.get(0).getAttribute("src");
            WebElement download = avatar.get(0);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            // roll down and keep the element to the center of browser
            js.executeScript("arguments[0].scrollIntoView(true);", download);
            Actions actions = new Actions(driver);
            actions.moveToElement(download).contextClick().build().perform();
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_DOWN);
            Thread.sleep(100);
            robot.keyPress(KeyEvent.VK_DOWN);
            Thread.sleep(100);
            robot.keyPress(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }
    }


    private static void downloadImg(String url, String fileBath) {
        HttpClient client = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        HttpParams httpRequestParameters = httpget.getParams();
        httpRequestParameters.setParameter(ClientPNames.HANDLE_REDIRECTS, false);
        httpget.setParams(httpRequestParameters);
        BasicHttpContext localContext = new BasicHttpContext();

        HttpResponse response = null;
        try {
            response = client.execute(httpget, localContext);
            FileUtils.copyInputStreamToFile(response.getEntity().getContent(), new File(fileBath));
            response.getEntity().getContent().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private String downloader(WebElement element, String attribute) throws IOException, NullPointerException, URISyntaxException {
//        String fileToDownloadLocation = element.getAttribute(attribute);
//        MyLog.logger.info("attribute: " + fileToDownloadLocation);
//        if (fileToDownloadLocation.trim().equals(""))
//            throw new NullPointerException("The element you have specified does not link to anything!");
//        URL fileToDownload = new URL(fileToDownloadLocation);
//        MyLog.logger.info("file: " + fileToDownload.getFile());
//        File downloadedFile = new File(this.localDownloadPath + fileToDownload.getFile().replaceFirst("(/|\\\\).*(/|\\\\)", ""));
//        if (downloadedFile.canWrite() == false) downloadedFile.setWritable(true);
//        HttpClient client = new DefaultHttpClient();
//        BasicHttpContext localContext = new BasicHttpContext();
//        MyLog.logger.info("Mimic WebDriver cookie state: " + this.mimicWebDriverCookieState);
//        if (this.mimicWebDriverCookieState) {
//            localContext.setAttribute(ClientContext.COOKIE_STORE, mimicCookieState(this.driver.manage().getCookies()));
//        }
//        HttpGet httpget = new HttpGet(fileToDownload.toURI());
//        HttpParams httpRequestParameters = httpget.getParams();
//        httpRequestParameters.setParameter(ClientPNames.HANDLE_REDIRECTS, false);
//        httpget.setParams(httpRequestParameters);
//        HttpResponse response = client.execute(httpget, localContext);
//        this.httpStatusOfLastDownloadAttempt = response.getStatusLine().getStatusCode();
//        MyLog.logger.info("HTTP GET request status: " + this.httpStatusOfLastDownloadAttempt);
//        if (this.httpStatusOfLastDownloadAttempt == 200) {
//            MyLog.logger.info("Downloading file: " + downloadedFile.getName());
//            FileUtils.copyInputStreamToFile(response.getEntity().getContent(), downloadedFile);
//            response.getEntity().getContent().close();
//            String downloadedFileAbsolutePath = downloadedFile.getAbsolutePath();
//            MyLog.logger.info("File downloaded to '" + downloadedFileAbsolutePath + "'");
//            return downloadedFileAbsolutePath;
//        }
//        return "";
//    }


    public static void getFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public void testThread() {
        ExecutorService pool = Executors.newCachedThreadPool();
//        MyRunnable t1 = new MyRunnable();
        MyThread t1 = new MyThread();
        final Future<?> task = pool.submit(t1);
        try {
            Thread.sleep(550);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.cancel(true);
//        System.out.println(getValueFormFuture(task));
        System.out.println("这句将在task.get()阻塞结束后执行！");
        System.out.println(task.isCancelled());
        System.out.println(task.isDone());
        //关闭线程池
        pool.shutdown();
    }

    class MyThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println(Thread.currentThread().getName() + "正在执行。。。");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("任务被打断！");
                }
            }
        }
    }

    class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 400; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("任务被打断！");
                    return;
                }
                System.out.println("num=" + i);
            }
        }
    }


    private static String getValueFormFuture(FutureTask<String> task) {
        String str = "defaultValue";
        try {
            str = task.get();// task.get()会阻塞当前线程，等待子线程结束。
        } catch (Exception e) {
            System.out.println("任务已经被取消！");
        }
        return str;
    }

    public static void testRex() {
        String regex = "<[^>]*>";
        Pattern p = Pattern.compile(regex);

        String source = "<span class=\"emoji emoji2764\"></span>冰心<span class=\"emoji emoji2764\"></span><span class=\"emoji emoji1f510\"></span><span class=\"emoji emoji1f511\"></span>";
        Matcher m = p.matcher(source);
        String s = m.replaceAll("");
        System.out.println(s);
        while (m.find()) {
            System.out.println(m.group(0));
        }
    }

}
