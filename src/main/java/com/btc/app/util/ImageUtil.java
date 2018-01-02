package com.btc.app.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class ImageUtil
{
	  public static Map<String, Object> imagePath(MultipartFile file, String path, HttpServletRequest request, HttpServletResponse response)
	  {
	    Map<String, Object> returnMap = new HashMap<String, Object>();
	    try
	    {
	      String fileName = file.getOriginalFilename();
	      String realPath = request.getSession().getServletContext().getRealPath("/");

	      String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;

	      path = realPath + "resource\\" + path + "\\" + trueFileName;
	      System.out.println("存放图片文件的路径:" + path);

	      file.transferTo(new File(path));
	      returnMap.put("flag", Boolean.valueOf(true));
	      returnMap.put("path", path);
	      return returnMap;
	    } catch (IllegalStateException|IOException e) {
	      e.printStackTrace();
	      returnMap.put("flag", Boolean.valueOf(false));
	    }return returnMap;
	  }

	  public static Map<String, Object> imagePath2(MultipartFile multipartFile, String path, HttpServletRequest request, HttpServletResponse response)
 {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		try {
			// SimpleDateFormat dateformat = new
			// SimpleDateFormat("yyyy/MM/dd/HH");

			String logoPathDir = "/backupscreen";

			// String logoRealPathDir =
			// request.getSession().getServletContext().getRealPath(logoPathDir);

			//String logoRealPathDir = new File(request.getSession().getServletContext().getRealPath("/")).getParent() + logoPathDir;// 图片备份目录
			String logoRealPathDir = new File(request.getSession().getServletContext().getRealPath("/")) + logoPathDir;// 图片备份目录

			File logoSaveFile = new File(logoRealPathDir);
			if (!logoSaveFile.exists())
				logoSaveFile.mkdirs();
			String saveUrl = new File(request.getContextPath()) + logoPathDir + "/";
			//String saveUrl = logoPathDir + "/";

			// String suffix =
			// multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));

			String logImageName = multipartFile.getOriginalFilename();
			if (logImageName.equals("")) {
				paramMap.put("flag", Boolean.valueOf(false));
				return paramMap;
			}

			String type = logImageName.indexOf(".") != -1 ? logImageName.substring(logImageName.lastIndexOf(".") + 1, logImageName.length()) : null;

			if (type == null) {
				paramMap.put("flag", Boolean.valueOf(false));
				return paramMap;
			} else if (!("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase()))) {
				paramMap.put("flag", Boolean.valueOf(false));
				return paramMap;
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String fileExt = logImageName.substring(
					logImageName.lastIndexOf(".") + 1).toLowerCase();
			String newFileName = sdf.format(new Date()) + "_"
					+ new Random().nextInt(1000) + "." + fileExt;

			String fileName = logoRealPathDir + File.separator + newFileName;
			File file = new File(fileName);
			try {
				multipartFile.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			saveUrl = saveUrl + "" + newFileName;
			System.out.println(saveUrl);
			paramMap.put("flag", Boolean.valueOf(true));
			paramMap.put("path", saveUrl);
		} catch (Exception e) {
			e.printStackTrace();
			paramMap.put("flag", Boolean.valueOf(false));
			return paramMap;
		}
		return paramMap;
	}

	public static boolean deleteFile(String path, HttpServletRequest request) {
		try {
			// String logoPathDir = "/screen";
			// String logoRealPathDir =
			// request.getSession().getServletContext().getRealPath("/");
			String logoPathDir = "/backupscreen";
			String logoRealPathDir = new File(request.getSession().getServletContext().getRealPath("/")).getParent() + logoPathDir;// 图片备份目录
			String fileName = logoRealPathDir + File.separator + path.split("/")[2];
			File file = new File(fileName);
			file.delete();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}