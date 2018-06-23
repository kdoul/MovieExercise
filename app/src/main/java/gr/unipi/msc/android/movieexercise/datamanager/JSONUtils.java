package gr.unipi.msc.android.movieexercise.datamanager;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public abstract class JSONUtils<M> {
    protected Gson gson = new Gson();

    public String serializeModel(M model) {
        return gson.toJson(model);
    }

    public List<String> serializeModels(List<M> models) {
        List<String> serializedModels = new ArrayList<>();

        for(M model : models) {
            serializedModels.add(serializeModel(model));
        }

        return serializedModels;
    }

    public abstract M deserializeModel(String serializedModel);

    public List<M> deserializeModels(List<String> serializedModels) {
        List<M> deserializedModels = new ArrayList<>();

        for(String serializedModel : serializedModels) {
            deserializedModels.add(deserializeModel(serializedModel));
        }

        return deserializedModels;
    }
}