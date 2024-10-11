package com.bezkoder.spring.datajpa.service;

import com.bezkoder.spring.datajpa.model.Asset;
import com.bezkoder.spring.datajpa.model.Tutorial;
import com.bezkoder.spring.datajpa.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    public Asset saveAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    public void deleteAsset(Long id) {
        assetRepository.deleteById(id);
    }

    public Asset editAsset(Long assetId, Asset asset) {
        Optional<Asset> assetData = assetRepository.findById(assetId);
        Asset _asset = null;
        if (assetData.isPresent()) {
            _asset = assetData.get();
            _asset = asset;
            assetRepository.save(_asset);
        }
        return _asset;
    }
}
