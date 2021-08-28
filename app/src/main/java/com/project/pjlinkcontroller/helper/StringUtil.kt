package com.project.pjlinkcontroller.helper

class StringUtil {
    companion object{
        fun StringToHexString(s:String):String
        {
            val stringBuilder = StringBuilder()
            for(ch in s)
            {
                val temp = Integer.toHexString(ch.code)
                stringBuilder.append(temp)
            }
            return stringBuilder.toString()
        }

        fun hexStringToByte(s:String):ByteArray
        {
            val hexString = "0123456789ABCDE"
            val byteArray = ByteArray(s.length/2 + 1) // add 1 byte for CR
            val length = byteArray.size - 1;
            for(i in 0 until length)
            {
                var index = i*2
                val upper = hexString.indexOf(s[index])
                val down = hexString.indexOf(s[index+1])
                val value = upper.shl(4) or down
                byteArray[i] = value.toByte();
            }
            byteArray[length] = 0x0d;
            return byteArray
        }
    }
}