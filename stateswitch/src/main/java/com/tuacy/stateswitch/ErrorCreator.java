package com.tuacy.stateswitch;

import android.content.Context;
import android.support.annotation.NonNull;

public interface ErrorCreator {

	@NonNull
	StateErrorInterface createStateError(@NonNull Context context, @NonNull StateLayout layout);
}
