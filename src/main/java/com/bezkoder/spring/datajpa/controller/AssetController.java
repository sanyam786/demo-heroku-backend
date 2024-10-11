package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.Asset;
import com.bezkoder.spring.datajpa.model.Tutorial;
import com.bezkoder.spring.datajpa.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assets")
//@CrossOrigin(origins = "http://localhost:8081")
@CrossOrigin(origins = "https://findash-25315.web.app")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @GetMapping
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }

    @PostMapping
    public Asset addAsset(@RequestBody Asset asset) {
        return assetService.saveAsset(asset);
    }

    @DeleteMapping("/{id}")
    public void deleteAsset(@PathVariable Long id) {
        assetService.deleteAsset(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asset> editAsset(@PathVariable("id") long id, @RequestBody Asset asset) {
        Asset _asset = assetService.editAsset(id, asset);
        if (_asset != null) {
            return new ResponseEntity<>(_asset, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
