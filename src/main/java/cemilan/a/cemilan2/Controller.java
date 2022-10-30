/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cemilan.a.cemilan2;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author LENOVO
 */
@Controller
public class Controller {
    @RequestMapping("/alo")
   
    public String register(){
        return "index";
    }
    @PostMapping("/save")
    public String save(
            @RequestParam("nama") String nama,
            @RequestParam("lokasi") String lokasi,
            @RequestParam("gambar") MultipartFile gambar,
            ModelMap model){
        
        Index index = new Index();
        index.setNama(nama);
        index.setLokasi(lokasi);
        model.addAttribute("nama", nama);
        model.addAttribute("lokasi", lokasi);
        
        ;
        if (gambar.isEmpty()){
            return "index";
        }
        Path path = Paths.get("uploads/");
        try{
            InputStream inputStream = gambar.getInputStream();
            Files.copy(inputStream, path.resolve(gambar.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            index.setGambar(gambar.getOriginalFilename().toLowerCase());
            //
            model.addAttribute("INFOR", index);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "viewpage";
    }
}
