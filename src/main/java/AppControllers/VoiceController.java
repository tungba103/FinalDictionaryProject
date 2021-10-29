package AppControllers;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.util.Objects;

public class VoiceController {

  public static void speakEnglish(String spelling) {
    System.setProperty("freetts.voices",
        "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
    Voice voice;
    System.out.println("Speaking");
    voice = VoiceManager.getInstance().getVoice("kevin");
    if (voice != null) {
      voice.allocate();
    }
    try {
      Objects.requireNonNull(voice).setRate(140);
      voice.setPitch(100);
      voice.setVolume(100);
      voice.speak(spelling);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

