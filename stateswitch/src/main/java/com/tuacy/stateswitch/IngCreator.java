package com.tuacy.stateswitch;

import android.content.Context;
import android.support.annotation.NonNull;

public interface IngCreator {

	@NonNull
	StateIngInterface createStateIng(@NonNull Context context, @NonNull StateLayout layout);
}
