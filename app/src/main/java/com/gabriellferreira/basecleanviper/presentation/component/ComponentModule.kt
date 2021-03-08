package com.gabriellferreira.basecleanviper.presentation.component

import com.gabriellferreira.basecleanviper.presentation.app.scope.ControllerScope
import com.gabriellferreira.basecleanviper.presentation.feature.core.progressDialog.ProgressDialog
import dagger.Module
import dagger.Provides

@Module
class ComponentModule {

    @Provides
    @ControllerScope
    fun provideProgressDialog(): ProgressDialog = ProgressDialog()
}