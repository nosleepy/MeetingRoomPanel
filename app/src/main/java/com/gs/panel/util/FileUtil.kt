package com.gs.panel.util

import com.gs.panel.PanelApplication
import java.security.KeyFactory
import java.security.Signature
import java.security.spec.PKCS8EncodedKeySpec
import java.util.Base64

object FileUtil {
    fun getUsername(): String {
        return Base64.getEncoder().encodeToString(readAssetFileToByteArray("factory_cert.pem"))
    }

    fun getPassword(): String {
        val buffer = readAssetFileToByteArray("factory_key.der")
        val spec = PKCS8EncodedKeySpec(buffer)
        val keyFactory = KeyFactory.getInstance("EC")
        val privateKey = keyFactory.generatePrivate(spec)
        val data = "C074AD7BEC40&22V9PVCMC27BEC40"
        val signature = Signature.getInstance("SHA384withECDSA")
        signature.initSign(privateKey)
        signature.update(data.toByteArray())
        val signedData = signature.sign()
        return Base64.getEncoder().encodeToString(signedData)
    }

    private fun readAssetFileToByteArray(fileName: String): ByteArray {
        val inputStream = PanelApplication.context.assets.open(fileName)
        val buffer = ByteArray(inputStream.available())
        inputStream.read(buffer)
        inputStream.close()
        return buffer
    }
}